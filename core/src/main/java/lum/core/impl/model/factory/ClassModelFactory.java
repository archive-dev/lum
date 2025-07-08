package lum.core.impl.model.factory;

import lum.antlr4.LumParser;
import lum.core.impl.model.*;
import lum.core.model.ClassModel;
import lum.core.model.Member;
import lum.core.util.Utils;
import lum.lang.struct.Either;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

/**
 * Factory for creating ClassModel instances from various sources.
 * Handles both Java reflection-based and file-based class model creation.
 */
public class ClassModelFactory {
    private final ModelCache modelCache;
    private final MethodModelFactory methodModelFactory;
    private final FieldModelFactory fieldModelFactory;
    
    public ClassModelFactory(ModelCache modelCache) {
        this.modelCache = modelCache;
        this.methodModelFactory = new MethodModelFactory();
        this.fieldModelFactory = new FieldModelFactory();
    }
    
    /**
     * Creates a ClassModel from a Java Class using reflection.
     */
    public Optional<ClassModel> createFromClass(Class<?> clazz) {
        if (clazz == null) {
            return Optional.empty();
        }
        
        clazz = Utils.getComponentType(clazz);
        
        // Check cache first
        Optional<ClassModel> cached = modelCache.getClassModel(clazz);
        if (cached.isPresent()) {
            return cached;
        }
        
        ClassModel model = buildClassModel(clazz);
        if (model != null) {
            modelCache.putClassModel(clazz, model);
            
            // Process the model if not already processed
            if (!modelCache.isProcessed(model)) {
                processClassModel(model, clazz);
                modelCache.markAsProcessed(model);
            }
        }
        
        return Optional.ofNullable(model);
    }
    
    /**
     * Creates a ClassModel from a file path.
     */
    public Optional<ClassModel> createFromFile(@Nullable Path workDir, Path path) {
//        if (workDir != null)
//            Utils.subtractPaths(path, workDir);
        String pkg = path.getParent().toString().replaceAll("\\%s".formatted(File.separator), ".");

        // Check cache first
        Optional<ClassModel> cached = modelCache.getClassModel(path);
        if (cached.isPresent()) {
            return cached;
        }
        
        try {
            Member[] fileMembers = FileParser.parseFile(workDir, path).orElseThrow();
            ClassModel classModel = new ClassModelImpl(
                    Utils.EMPTY_ATTRIBUTE_MODELS,
                    new AccessFlag[]{AccessFlag.FINAL, AccessFlag.PUBLIC},
                    pkg + "." + path.getFileName().toString().replaceAll("\\..*", "") + "Lum",
                    Optional.empty(),
                    createFromClass(Object.class),
                    Utils.EMPTY_CLASS_MODELS,
                    Arrays.stream(fileMembers).filter(m -> !(m instanceof ClassModel)).toArray(Member[]::new),
                    Either.<Class<?>, LumParser.TypeDeclarationContext>right(null).right()
            );

            modelCache.putClassModel(path, classModel);
            return Optional.of(classModel);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
    /**
     * Creates multiple ClassModels from a file containing multiple class declarations.
     */
    public Optional<ClassModel[]> createMultipleFromFile(@Nullable Path workDir, Path path) {
        String pkg = "";

        if (workDir != null) {
            pkg = Utils.subtractPaths(path, workDir).toString()
                    .replaceAll("\\..*", "")
                    .replaceAll("\\%s".formatted(File.separator), ".");
        }

        Optional<Member[]> fileMembers = FileParser.parseFile(workDir, path);
        if (fileMembers.isEmpty()) {
            return Optional.empty();
        }

        var classes = Arrays.stream(fileMembers.get())
                .filter(m -> m instanceof ClassModelImpl)
                .toArray(ClassModelImpl[]::new);
        var typeProcessor = ClassModelParser.getTypeProcessor(FileParser.getImports(path)).orElseThrow();

        for (ClassModelImpl model : classes) {
            var processor = new ClassModelProcessor(model, typeProcessor);
            processor.processClassModel();
        }

        var ret = new ClassModel[classes.length + 1];
        System.arraycopy(classes, 0, ret, 0, classes.length);
        ret[classes.length] = createFromFile(workDir, path).orElseThrow();

        return Optional.of(ret);
    }
    
    private ClassModel buildClassModel(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }

        if (clazz.isPrimitive()) {
            return new PrimitiveClassModelImpl(clazz.getName());
        }

        return new ClassModelImpl(
                Utils.EMPTY_ATTRIBUTE_MODELS,
                clazz.accessFlags().toArray(AccessFlag[]::new),
                clazz.getName(),
                Optional.empty(),
                createFromClass(clazz.getSuperclass()),
                new ClassModel[clazz.getInterfaces().length],
                new Member[clazz.getMethods().length + clazz.getFields().length + clazz.getConstructors().length],
                clazz
        );
    }
    
    private void processClassModel(ClassModel model, Class<?> clazz) {
        processInterfaces(model, clazz);
        processMembers(model, clazz);
    }
    
    private void processInterfaces(ClassModel model, Class<?> clazz) {
        ClassModel[] interfaces = Arrays.stream(clazz.getInterfaces())
                .map(this::createFromClass)
                .map(Optional::orElseThrow)
                .toArray(ClassModel[]::new);

        System.arraycopy(interfaces, 0, model.interfaces(), 0, interfaces.length);
    }
    
    private void processMembers(ClassModel model, Class<?> clazz) {
        var members = Utils.getClassMembers(clazz);
        for (int i = 0; i < members.length; i++) {
            var member = members[i];
            switch (member) {
                case Method method -> model.members()[i] = methodModelFactory.createFromMethod(method);
                case Constructor<?> constructor -> model.members()[i] = methodModelFactory.createFromConstructor(constructor);
                case Field field -> model.members()[i] = fieldModelFactory.createFromField(field);
                case null, default -> {}
            }
        }
    }
}