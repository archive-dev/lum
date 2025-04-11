package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import lum.core.util.TypeModelList;
import lum.core.util.Utils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

final class ModelCache {
    private static final Map<Class<?>, ClassModel> classPool = new ConcurrentHashMap<>();
    static final Map<ClassPath, ClassModel> pathPool = new ConcurrentHashMap<>();

    private static final Map<ClassModel, Map<String, Map<List<TypeModel>, MethodModel>>> classModelMethods = new ConcurrentHashMap<>();
    private static final Map<ClassModel, Map<String, FieldModel>> classModelFields = new ConcurrentHashMap<>();

    private static final Map<ClassModel, Map<Integer, TypeModel>> typeModelsCache = new ConcurrentHashMap<>();

    static final Map<ClassModel, LumParser.ClassDeclarationContext> classContexts = new HashMap<>();
    static final Map<ClassModel, LumParser.InterfaceDeclarationContext> interfaceContexts = new HashMap<>();
    static final Map<MethodModel, LumParser.BlockContext> methodContexts = new HashMap<>();

    static final Map<TypeVariable<?>, GenericArgument> genericArguments = new HashMap<>();

    private ModelCache() {}

    // Class caching methods
    public static boolean containsClass(Class<?> clazz) {
        return classPool.containsKey(clazz);
    }

    public static boolean containsClass(ClassPath path) {
        return pathPool.containsKey(path);
    }

    public static boolean containsTypeModel(ClassModel model, int arrayDimensions) {
        return typeModelsCache.containsKey(model) && typeModelsCache.get(model).containsKey(arrayDimensions);
    }

    public static ClassModel getClass(Class<?> clazz) {
        if (clazz == null) return null;
        clazz = Utils.getComponentType(clazz);
        if (classPool.containsKey(clazz)) {
            return classPool.get(clazz);
        }
        var model = ModelFactory.createClassModel(clazz);
        classPool.put(clazz, model);
        return model;
    }

    public static TypeModel getTypeModel(Class<?> clazz) {
        var model = getClass(clazz);

        int arrayDimensions = Utils.getArrayDepth(clazz);
        return getTypeModel(model, arrayDimensions);
    }

    public static TypeModel getTypeModel(ClassModel model, int arrayDimensions) {
        if (!containsTypeModel(model, arrayDimensions)) {
            var typeModel = model.typeModel(arrayDimensions);
            cacheTypeModel(typeModel);
        }

        return typeModelsCache.get(model).get(arrayDimensions);
    }

    /// Retrieves a ClassModel instance based on a list of path elements representing the class's fully qualified name.
    ///
    /// This method first attempts to load the class using [#forName(String)] with the path elements joined by dots.
    /// If the class is not found in the classpath, it then tries to create a ClassPath object using [#createClassPath(List)]
    /// and retrieves the ClassModel from the [#pathPool] cache. If the ClassModel is not already in the cache, it is created using
    /// [#createClassModel(ClassPath)] and added to the cache.
    ///
    ///
    /// @param pathElements A list of strings representing the fully qualified name of the class (e.g., ["com", "example", "MyClass"]).
    /// @return The ClassModel instance representing the class.
    /// @throws RuntimeException If a ClassNotFoundException occurs when using [#forName(String)],
    ///                            if a FileNotFoundException occurs when creating the ClassPath,
    ///                            or if an IOException occurs when creating the ClassModel using [#createClassModel(ClassPath)].
    public static ClassModel getClassFromPath(List<String> pathElements) {
        try {
            return getClass(Class.forName(String.join(".", pathElements)));
        } catch (ClassNotFoundException ex) {
            try {
                ClassPath path = ModelFactory.createClassPath(pathElements);
                if (pathPool.containsKey(path)) return pathPool.get(path);

                try {
                    var model = ClassModelBuilder.createClassModel(path);
                    pathPool.put(path, model);
                    return pathPool.get(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ClassModel cacheClass(ClassModel model) {
        cacheMethodsForClass(model);
        cacheFieldsForClass(model);
        return model;
    }

    private static void cacheMethodsForClass(ClassModel model) {
        Map<String, Map<List<TypeModel>, MethodModel>> methodsMap = classModelMethods.computeIfAbsent(model, _ -> new ConcurrentHashMap<>());
        for (MethodModel method : model.methods()) {
            methodsMap.computeIfAbsent(method.name(), _ -> new ConcurrentHashMap<>())
                    .put(Arrays.stream(method.parameters()).map(ParameterModel::type).toList(), method);
        }
    }

    private static void cacheFieldsForClass(ClassModel model) {
        Map<String, FieldModel> fieldsMap = classModelFields.computeIfAbsent(model, _ -> new ConcurrentHashMap<>());
        for (FieldModel field : model.fields()) {
            fieldsMap.put(field.name(), field);
        }
    }

    public static void cacheTypeModel(TypeModel model) {
        var models = typeModelsCache.computeIfAbsent(model.model(), _ -> new ConcurrentHashMap<>());
        models.put(model.arrayDimensions(), model);
    }

    public static void cacheClass(Class<?> clazz, ClassModel model) {
        classPool.put(clazz, model);
    }

    public static void cacheClass(ClassPath path, ClassModel model) {
        pathPool.put(path, model);
    }

    // Method caching methods
    public static boolean containsMethod(Method method) {
        ClassModel classModel = ClassModel.of(method.getDeclaringClass());
        return classModelMethods.containsKey(classModel) &&
                classModelMethods.get(classModel).containsKey(method.getName()) &&
                classModelMethods.get(classModel).get(method.getName()).containsKey(
                        Arrays.stream(method.getParameters())
                                .map(p -> TypeModel.of(p.getType()))
                                .toList()
                );
    }

    public static void cacheMethod(MethodModel method) {
        classModelMethods
                .computeIfAbsent(method.owner(), _ -> new ConcurrentHashMap<>())
                .computeIfAbsent(method.name(), _ -> new ConcurrentHashMap<>())
                .putIfAbsent(
                        Arrays.stream(method.parameters())
                                .map(ParameterModel::type)
                                .toList(),
                        method
                );
    }

    public static MethodModel getMethodExact(ClassModel owner, String name, TypeModel... arguments) {
        if (!containsMethod(owner, name, arguments)) return null;
        return classModelMethods.get(owner).get(name).get(List.of(arguments));
    }

    public static MethodModel getMethod(ClassModel owner, String name, TypeModel... arguments) {
        return getMethod(owner, name, new TypeModelList(List.of(arguments)));
    }

    public static MethodModel getMethod(ClassModel owner, String name, TypeModelList arguments) {
        var args = containsMethod(owner, name, arguments);
        if (args == null) return null;
        return classModelMethods.get(owner).get(name).get(args);
    }

    public static Map<List<TypeModel>, MethodModel> getMethods(Class<?> clazz, String methodName) {
        ClassModel classModel = ClassModel.of(clazz);
        if (!containsMethods(classModel, methodName)) return null;

        return classModelMethods
                .get(classModel)
                .get(methodName);
    }

    public static MethodModel getMethodExact(Method method) {
        if (!containsMethod(method))
            cacheMethod(ModelFactory.createMethodModel(method));

        ClassModel classModel = ClassModel.of(method.getDeclaringClass());
        return classModelMethods
                .get(classModel)
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

    public static List<TypeModel> containsMethod(ClassModel owner, String name, TypeModelList arguments) {
//        cacheClass(owner);
        if (!classModelMethods.containsKey(owner) || !classModelMethods.get(owner).containsKey(name)) {
            return null;
        }

        var methodMap = classModelMethods.get(owner).get(name);

        return methodMap.keySet().stream()
                .filter(c -> new TypeModelList(c).isAssignableFrom(arguments))
                .findFirst()
                .orElse(null);
    }

    public static boolean containsMethods(ClassModel owner, String name) {
        return classModelMethods.containsKey(owner) &&
                classModelMethods.get(owner).containsKey(name);
    }

    public static boolean containsMethod(Constructor<?> constructor) {
        ClassModel classModel = ClassModel.of(constructor.getDeclaringClass());
        return classModelMethods.containsKey(classModel) &&
                classModelMethods.get(classModel).containsKey("<init>") &&
                classModelMethods.get(classModel).get("<init>").containsKey(
                        Arrays.stream(constructor.getParameters())
                                .map(p -> TypeModel.of(p.getType()))
                                .toList()
                );
    }

    public static MethodModel getMethodExact(Constructor<?> constructor) {
        if (!containsMethod(constructor))
            cacheMethod(ModelFactory.createMethodModel(constructor));

        ClassModel classModel = ClassModel.of(constructor.getDeclaringClass());
        return classModelMethods
                .get(classModel)
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
        ClassModel classModel = ClassModel.of(field.getDeclaringClass());
        return classModelFields.containsKey(classModel) &&
                classModelFields.get(classModel).containsKey(field.getName());
    }

    public static FieldModel getField(Field field) {
        if (!containsField(field))
            cacheField(ModelFactory.createFieldModel(field));

        ClassModel classModel = ClassModel.of(field.getDeclaringClass());
        return classModelFields.get(classModel).get(field.getName());
    }

    public static FieldModel getField(ClassModel owner, String fieldName) {
        if (!containsField(owner, fieldName)) return null;
        return classModelFields.get(owner).get(fieldName);
    }

    public static List<FieldModel> getFields(ClassModel owner) {
        if (!classModelFields.containsKey(owner))
            return List.of();

        return new ArrayList<>(classModelFields.get(owner).values());
    }

    public static void cacheField(FieldModel field) {
        classModelFields.computeIfAbsent(field.owner(), _ -> new ConcurrentHashMap<>())
                .put(field.name(), field);
    }

    public static boolean modelContainsFields(ClassModel model) {
        return classModelFields.containsKey(model);
    }

    public static Map<String, FieldModel> getModelFields(ClassModel model) {
        return classModelFields.get(model);
    }
    
    public static boolean modelContainsMethods(ClassModel model) {
        return classModelMethods.containsKey(model);
    }

    public static Map<String, Map<List<TypeModel>, MethodModel>> getModelMethods(ClassModel model) {
        return classModelMethods.get(model);
    }

    static Map<ClassModel, Map<String, Map<List<TypeModel>, MethodModel>>> getClassModelMethods() {
        return classModelMethods;
    }
}