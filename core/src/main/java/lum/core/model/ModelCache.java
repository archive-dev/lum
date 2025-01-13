package lum.core.model;

import lum.core.util.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class ModelCache {
    private static final HashMap<Class<?>, ClassModel> classPool = new HashMap<>();
    private static final HashMap<ClassPath, ClassModel> pathPool = new HashMap<>();
    private static final HashMap<Method, MethodModel> methodPool = new HashMap<>();
    private static final HashMap<Constructor<?>, MethodModel> constructorPool = new HashMap<>();
    private static final HashMap<Field, FieldModel> fieldPool = new HashMap<>();

    private static final HashMap<ClassModel, HashMap<String, HashMap<TypeModel[], MethodModel>>> classModelMethods = new HashMap<>();
    private static final HashMap<ClassModel, HashMap<String, FieldModel>> classModelFields = new HashMap<>();

    private ModelCache() {}

    // Class caching methods
    public static boolean containsClass(Class<?> clazz) {
        return classPool.containsKey(clazz);
    }

    public static boolean containsClass(ClassPath path) {
        return pathPool.containsKey(path);
    }

    public static ClassModel getClass(Class<?> clazz) {
        if (clazz == null) return null;
        clazz = Utils.getComponentType(clazz);
        if (!classPool.containsKey(clazz))
            classPool.put(clazz, ModelFactory.createClassModel(clazz));
        return classPool.get(clazz);
    }

    public static TypeModel getTypeModel(Class<?> clazz) {
        return getClass(clazz).typeModel(Utils.getArrayDepth(clazz));
    }

    public static ClassModel getClassFromPath(List<String> pathElements) {
        try {
            return getClass(Class.forName(String.join(".", pathElements)));
        } catch (ClassNotFoundException ex) {
            try {
                ClassPath path = ModelFactory.createClassPath(pathElements);
                return pathPool.computeIfAbsent(path, p -> {
                    try {
                        return ParserModelFactory.createClassModel(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Failed to get class model from path", e);
            }
        }
    }

    public static void cacheClass(Class<?> clazz, ClassModel model) {
        classPool.put(clazz, model);
    }

    public static void cacheClass(ClassPath path, ClassModel model) {
        pathPool.put(path, model);
    }

    // Method caching methods
    public static boolean containsMethod(Method method) {
        return methodPool.containsKey(method);
    }

    public static void cacheMethod(MethodModel method) {
        classModelMethods
                .computeIfAbsent(method.owner(), _ -> new HashMap<>())
                .computeIfAbsent(method.name(), _ -> new HashMap<>())
                .put(
                        Arrays.stream(method.parameters())
                                .map(ParameterModel::type)
                                .toArray(TypeModel[]::new),
                        method
                );
    }

    public static MethodModel getMethod(ClassModel owner, String name, TypeModel... arguments) {
        return classModelMethods.get(owner).get(name).get(arguments);
    }

    public static MethodModel getMethod(Method method) {
        if (!methodPool.containsKey(method))
            methodPool.put(method, ModelFactory.createMethodModel(method));
        return methodPool.get(method);
    }

    // Constructor caching methods
    public static boolean containsConstructor(Constructor<?> constructor) {
        return constructorPool.containsKey(constructor);
    }

    public static MethodModel getConstructor(Constructor<?> constructor) {
        if (!constructorPool.containsKey(constructor))
            constructorPool.put(constructor, ModelFactory.createMethodModel(constructor));
        return constructorPool.get(constructor);
    }

    // Field caching methods
    public static boolean containsField(Field field) {
        return fieldPool.containsKey(field);
    }

    public static FieldModel getField(Field field) {
        if (!fieldPool.containsKey(field))
            fieldPool.put(field, ModelFactory.createFieldModel(field));
        return fieldPool.get(field);
    }

    public static void cacheField(FieldModel field) {
        classModelFields.computeIfAbsent(field.owner(), _ -> new HashMap<>())
                .put(field.name(), field);
    }

    // Clear all caches
    public static void clearAll() {
        classPool.clear();
        pathPool.clear();
        methodPool.clear();
        constructorPool.clear();
        fieldPool.clear();
    }
}