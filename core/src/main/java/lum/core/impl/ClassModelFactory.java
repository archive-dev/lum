package lum.core.impl;

import lum.core.model.ClassModel;
import lum.core.model.Member;
import lum.core.model.TypeModel;
import lum.core.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.*;

public final class ClassModelFactory {
    private ClassModelFactory() {}

    private static final Map<Class<?>, @NotNull ClassModel> classCache = new HashMap<>();
    private static final Map<Path, @NotNull ClassModel> classCache2 = new HashMap<>();
    private static final Set<@NotNull ClassModel> processedClassModels = new HashSet<>();

    public static Optional<TypeModel> typeModelOf(Class<?> clazz) {
        return of(clazz).map(ClassModel::asTypeModel);
    }

    public static Optional<ClassModel> ofFile(Path path) {
        if (classCache2.containsKey(path))
            return Optional.of(classCache2.get(path));
        try {
            Member[] fileMembers = FileParser.parseFile(path).orElseThrow();
            ClassModel classModel = new ClassModelImpl(
                    Utils.EMPTY_ATTRIBUTE_MODELS,
                    new AccessFlag[]{AccessFlag.FINAL, AccessFlag.PUBLIC},
                    path.getFileName().toString().replaceAll("\\..*", "") + "Lum",
                    Optional.empty(),
                    of(Object.class),
                    Utils.EMPTY_CLASS_MODELS,
                    Arrays.stream(fileMembers).filter(m -> !(m instanceof ClassModel)).toArray(Member[]::new)
            );

            classCache2.put(path, classModel);
            return Optional.of(classModel);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<ClassModel[]> classesFromFile(Path path) {
        Optional<Member[]> fileMembers = FileParser.parseFile(path);
        if (fileMembers.isEmpty())
            return Optional.empty();

        var classes = Arrays.stream(fileMembers.get())
                .filter(m -> m instanceof ClassModelImpl)
                .toArray(ClassModelImpl[]::new);
        var typeProcessor = ClassModelParser.getTypeProcessor(FileParser.getImports(path)).orElseThrow();

        for (ClassModelImpl model : classes) {
            var processor = new ClassModelProcessor(model, typeProcessor);
            processor.processClassModel();
        }

        var ret = new ClassModel[classes.length+1];
        System.arraycopy(classes, 0, ret, 0, classes.length);
        ret[classes.length] = ofFile(path).orElseThrow();

        return Optional.of(ret);
    }

    public static Optional<ClassModel> of(Class<?> clazz) {
        if (clazz == null)
            return Optional.empty();
        clazz = Utils.getComponentType(clazz);
        var model = buildModel(clazz);
        if (model.isPresent() && !processedClassModels.contains(model.get()))
            processClassModel(model.get(), clazz);
        return model;
    }

    private static Optional<ClassModel> buildModel(Class<?> clazz) {
        if (clazz == null)
            return Optional.empty();

        if (classCache.containsKey(clazz))
            return Optional.of(classCache.get(clazz));

        if (clazz.isPrimitive())
            return Optional.of(buildPrimitive(clazz));

        ClassModel model = new ClassModelImpl(
                Utils.EMPTY_ATTRIBUTE_MODELS,
                clazz.accessFlags().toArray(AccessFlag[]::new),
                clazz.getName(),
                Optional.empty(),
                of(clazz.getSuperclass()),
                new ClassModel[clazz.getInterfaces().length],
                new Member[clazz.getMethods().length + clazz.getFields().length + clazz.getConstructors().length]
        );
        classCache.put(clazz, model);

        return Optional.of(model);
    }

    private static ClassModel buildPrimitive(Class<?> clazz) {
        return new PrimitiveClassModelImpl(clazz.getName());
    }

    private static void processClassModel(ClassModel model, Class<?> clazz) {
        processedClassModels.add(model);

        processInterfaces(model, clazz);
        processMembers(model, clazz);
    }

    private static void processInterfaces(ClassModel model, Class<?> clazz) {
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        ClassModel[] interfaces = Arrays.stream(clazz.getInterfaces())
                .map(ClassModelFactory::of)
                .map(Optional::get)
                .toArray(ClassModel[]::new);

        System.arraycopy(interfaces, 0, model.interfaces(), 0, interfaces.length);
    }

    private static void processMembers(ClassModel model, Class<?> clazz) {
        var members = Utils.getClassMembers(clazz);
        for (int i = 0; i < members.length; i++) {
            var member = members[i];
            switch (member) {
                case Method method -> {
                    model.members()[i] = MethodModelFactory.of(method);
                }
                case Constructor<?> method -> {
                    model.members()[i] = MethodModelFactory.of(method);
                }
                case Field field -> {
                    model.members()[i] = FieldModelFactory.of(field);
                }
                case null, default -> {}
            }
        }
    }
}
