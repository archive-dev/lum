package lum.core.model;

import lum.core.util.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

final class ModelCache {
    private static final HashMap<Class<?>, ClassModel> classPool = new HashMap<>();
    private static final HashMap<ClassPath, ClassModel> pathPool = new HashMap<>();

    static final HashMap<ClassModel, HashMap<String, HashMap<List<TypeModel>, MethodModel>>> classModelMethods = new HashMap<>();
    static final HashMap<ClassModel, HashMap<String, FieldModel>> classModelFields = new HashMap<>();

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
                throw new RuntimeException("Failed to getType class model from path", e);
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
        if (!classModelMethods.containsKey(ClassModel.of(method.getDeclaringClass()))) {
            classModelMethods.put(ClassModel.of(method.getDeclaringClass()), new HashMap<>());
            return false;
        }

        if (!classModelMethods.get(ClassModel.of(method.getDeclaringClass())).containsKey(method.getName())) {
            classModelMethods.get(ClassModel.of(method.getDeclaringClass())).put(method.getName(), new HashMap<>());
            return false;
        }

        return classModelMethods.get(ClassModel.of(method.getDeclaringClass())).get(method.getName()).containsKey(
                Arrays.stream(method.getParameters())
                        .map(p -> TypeModel.of(p.getType()))
                        .toList()
        );
    }

    public static void cacheMethod(MethodModel method) {
        classModelMethods
                .computeIfAbsent(method.owner(), _ -> new HashMap<>())
                .computeIfAbsent(method.name(), _ -> new HashMap<>())
                .put(
                        Arrays.stream(method.parameters())
                                .map(ParameterModel::type)
                                .toList(),
                        method
                );
    }

    public static MethodModel getMethod(ClassModel owner, String name, TypeModel... arguments) {
        if (!containsMethod(owner, name, arguments)) return null;
        return classModelMethods.get(owner).get(name).get(List.of(arguments));
    }

    public static MethodModel getMethod(Method method) {
        if (!containsMethod(method))
            cacheMethod(ModelFactory.createMethodModel(method));

        return classModelMethods
                .get(ClassModel.of(method.getDeclaringClass()))
                .get(method.getName())
                .get(
                        Arrays.stream(method.getParameterTypes())
                                .map(TypeModel::of)
                                .toList()
                );
    }

    // Constructor caching methods
    public static boolean containsMethod(ClassModel owner, String name, TypeModel... arguments) {
        return classModelMethods.containsKey(owner) &&
               classModelMethods.get(owner).containsKey(name) &&
               classModelMethods.get(owner).get(name).containsKey(List.of(arguments));
    }

    public static boolean containsMethod(Constructor<?> constructor) {
        if (!classModelMethods.containsKey(ClassModel.of(constructor.getDeclaringClass()))) {
            classModelMethods.put(ClassModel.of(constructor.getDeclaringClass()), new HashMap<>());
            return false;
        }

        if (!classModelMethods.get(ClassModel.of(constructor.getDeclaringClass())).containsKey("<init>")) {
            classModelMethods.get(ClassModel.of(constructor.getDeclaringClass())).put("<init>", new HashMap<>());
            return false;
        }

        return classModelMethods.get(ClassModel.of(constructor.getDeclaringClass())).get("<init>").containsKey(
                Arrays.stream(constructor.getParameters())
                        .map(p -> TypeModel.of(p.getType()))
                        .toList()
        );
    }

    public static MethodModel getMethod(Constructor<?> constructor) {
        if (!containsMethod(constructor))
            cacheMethod(ModelFactory.createMethodModel(constructor));

        return classModelMethods
                .get(ClassModel.of(constructor.getDeclaringClass()))
                .get("<init>")
                .get(
                        Arrays.stream(constructor.getParameterTypes())
                                .map(TypeModel::of)
                                .toList()
                );
    }

    // Field caching methods
    public static boolean containsField(ClassModel owner, String name) {
        return classModelFields.containsKey(owner) &&
                classModelFields.get(owner).containsKey(name);
    }

    public static boolean containsField(Field field) {
        if (!classModelFields.containsKey(ClassModel.of(field.getDeclaringClass()))) {
            classModelFields.put(ClassModel.of(field.getDeclaringClass()), new HashMap<>());
            return false;
        }

        return classModelFields.get(ClassModel.of(field.getDeclaringClass())).containsKey(field.getName());
    }

    public static FieldModel getField(Field field) {
        if (!containsField(field))
            cacheField(ModelFactory.createFieldModel(field));

        return classModelFields.get(ClassModel.of(field.getDeclaringClass())).get(field.getName());
    }

    public static FieldModel getField(ClassModel owner, String fieldName) {
        return classModelFields.get(owner).get(fieldName);
    }

    public static List<FieldModel> getFields(ClassModel owner) {
        if (classModelFields.containsKey(owner))
            return List.of();

        return new ArrayList<>(classModelFields.get(owner).values());
    }

    public static void cacheField(FieldModel field) {
        classModelFields.computeIfAbsent(field.owner(), _ -> new HashMap<>())
                .put(field.name(), field);
    }
}