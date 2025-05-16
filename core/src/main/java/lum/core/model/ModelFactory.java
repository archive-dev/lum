package lum.core.model;

import lum.core.util.Utils;

import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import static lum.core.util.Utils.EMPTY_ANNOTATION_MODELS;

final class ModelFactory {
    private ModelFactory() {}

    // Class Path creation
    public static ClassPath createClassPath(List<String> pathElements) throws FileNotFoundException {
        if (pathElements.isEmpty()) {
            throw new IllegalArgumentException("Path elements cannot be empty");
        }

        if (pathElements.size() == 1) {
            return createSingleElementClassPath(pathElements.getLast());
        }

        return createMultiElementClassPath(pathElements);
    }

    private static ClassPath createSingleElementClassPath(String element) throws FileNotFoundException {
        if (!Utils.fileExists(Path.of(element))) {
            throw new FileNotFoundException("File not found: " + element);
        }
        return new ClassPath(ModelConfig.workDir, element + ".lum", element);
    }

    private static ClassPath createMultiElementClassPath(List<String> elements) throws FileNotFoundException {
        Path path = ModelConfig.workDir.resolve(resolveFilePath(elements));
        List<String> pathComponents = getPathComponents(path);
        String fileName = getFileName(elements, pathComponents);
        String className = elements.getLast();

        return new ClassPath(path.getParent(), fileName, className);
    }

    private static Path resolveFilePath(List<String> elements) throws FileNotFoundException {
        Path path = Path.of(elements.getFirst(), elements.subList(1, elements.size()).toArray(String[]::new));
        int i = 1;
        while (!Utils.fileExists(path) && i < elements.size()) {
            path = Path.of(elements.getFirst(), elements.subList(1, elements.size()-i).toArray(String[]::new));
            i++;
        }
        if (!Utils.fileExists(path)) {
            throw new FileNotFoundException("Unable to resolve file path");
        }
        return path;
    }

    private static List<String> getPathComponents(Path path) {
        return StreamSupport.stream(path.spliterator(), false)
                .map(Path::toString)
                .limit(path.getNameCount()-1)
                .toList();
    }

    private static String getFileName(List<String> elements, List<String> pathComponents) {
        var fileNameElements = new ArrayList<>(elements);
        fileNameElements.removeAll(pathComponents);
        return fileNameElements.getFirst() + ".lum";
    }

    // Class Model Creation
    public static ClassModel createClassModel(Class<?> clazz) {
        clazz = Utils.getComponentType(clazz);
        if (ModelCache.containsClass(clazz)) {
            return ClassModel.of(clazz);
        }

        var model = buildInitialClassModel(clazz);
        ModelCache.cacheClass(clazz, model);
        populateClassModel(model, clazz);
        cacheClassMembers(clazz);

        return model;
    }

    private static ClassModel buildInitialClassModel(Class<?> clazz) {
        return new ClassModelImpl(
                clazz.getSimpleName(), clazz.getPackageName(),
                null,
                new ClassModel[clazz.getInterfaces().length],
                Utils.getAccessFlags(clazz.getModifiers()),
                new GenericArgument[clazz.getTypeParameters().length],
                EMPTY_ANNOTATION_MODELS,
                clazz.isInterface(),
                clazz.isPrimitive()
        );
    }

    private static void populateClassModel(ClassModel model, Class<?> clazz) {
        var interfaces = Arrays.stream(clazz.getInterfaces())
                .map(ClassModel::of)
                .toArray(ClassModel[]::new);
        System.arraycopy(interfaces, 0, model.interfaces(), 0, interfaces.length);

        if (!clazz.isPrimitive() && clazz != Object.class) {
            model.setSuperClass(ClassModel.of(clazz.getSuperclass() == null ? Object.class : clazz.getSuperclass()));
        }

        var generics = GenericArgument.of(clazz);
        System.arraycopy(generics, 0, model.genericArguments(), 0, generics.length);
    }

    private static void cacheClassMembers(Class<?> clazz) {
        for (Method method : clazz.getMethods()) {
            createMethodModel(method);
        }
        for (Constructor<?> constructor : clazz.getConstructors()) {
            createMethodModel(constructor);
        }

        for (Field field : clazz.getFields()) {
            createFieldModel(field);
        }
    }

    // Method Model Creation
    public static MethodModel createMethodModel(Method method) {
        var model = new MethodModelImpl(
                ClassModel.of(method.getDeclaringClass()),
                method.getName(),
                TypeModel.of(method.getReturnType()),
                createParameterModels(method.getParameters()),
                createExceptionModels(method.getExceptionTypes()),
                Utils.getAccessFlags(method.getModifiers()),
                GenericArgument.of(method),
                EMPTY_ANNOTATION_MODELS
        );
        ModelCache.cacheMethod(model);
        return model;
    }

    public static MethodModel createMethodModel(Constructor<?> constructor) {
        var model = new MethodModelImpl(
                ClassModel.of(constructor.getDeclaringClass()),
                "<init>",
                TypeModel.of(void.class),
                createParameterModels(constructor.getParameters()),
                createExceptionModels(constructor.getExceptionTypes()),
                Utils.getAccessFlags(constructor.getModifiers()),
                GenericArgument.of(constructor),
                EMPTY_ANNOTATION_MODELS
        );
        ModelCache.cacheMethod(model);
        return model;
    }

    // Field Model Creation
    public static FieldModel createFieldModel(Field field) {
        var model = new FieldModelImpl(
                ClassModel.of(field.getDeclaringClass()),
                field.getName(),
                TypeModel.of(field.getType()),
                Utils.getAccessFlags(field.getModifiers()),
                EMPTY_ANNOTATION_MODELS
        );
        ModelCache.cacheField(model);
        return model;
    }

    // Parameter Model Creation
    private static ParameterModel[] createParameterModels(Parameter[] parameters) {
        return Arrays.stream(parameters)
                .map(p -> new ParameterModelImpl(
                        p.getName(),
                        TypeModel.of(p.getType())
                ))
                .toArray(ParameterModel[]::new);
    }

    private static TypeModel[] createExceptionModels(Class<?>[] exceptionTypes) {
        return Arrays.stream(exceptionTypes)
                .map(TypeModel::of)
                .toArray(TypeModel[]::new);
    }

    // Annotations Model creation
    private static ClassModel[] createAnnotationModels(Annotation[] annotations) {
        return Arrays.stream(annotations).map(Annotation::annotationType).map(ClassModel::of).toArray(ClassModel[]::new);
    }
}