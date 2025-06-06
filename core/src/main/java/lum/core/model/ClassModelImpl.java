package lum.core.model;

import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.util.*;

final class ClassModelImpl extends ClassModel {
    private final String name;
    private final String pkg;
    ClassModel superClass;
    private final ClassModel[] interfaces;
    private final Set<AccessFlag> accessFlags;
    private final GenericArgument[] genericArguments;
    private final AnnotationModel[] annotations;
    private final boolean isInterface;
    private final boolean isPrimitive;
    private final boolean isAnnotation;
    private final boolean isEnum;
    private HashSet<MethodModel> methods = null;

    private final Map<String, FieldModel> fieldCache = null;
    private final boolean fieldCacheInitialized = false;

    public ClassModelImpl(
            String name, String pkg,
            ClassModel superClass,
            ClassModel[] interfaces,
            Set<AccessFlag> accessFlags,
            GenericArgument[] genericArgument,
            AnnotationModel[] annotations,
            boolean isInterface, boolean isPrimitive, boolean isAnnotation, boolean isEnum
    ) {
        this.name = name;
        this.pkg = pkg;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.accessFlags = accessFlags;
        this.genericArguments = genericArgument;
        this.annotations = annotations;
        this.isInterface = isInterface;
        this.isPrimitive = isPrimitive;
        this.isAnnotation = isAnnotation;
        this.isEnum = isEnum;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String pkg() {
        return pkg;
    }

    @Override
    public ClassModel superClass() {
        return superClass;
    }

    @Override
    void setSuperClass(ClassModel value) {
        superClass = value;
    }

    @Override
    public ClassModel[] interfaces() {
        return interfaces;
    }

    private final HashMap<ClassModel, Boolean> subclassCheckCache = new HashMap<>();

    @Override
    public boolean isSubclassOf(ClassModel other) {
        if (subclassCheckCache.containsKey(other))
            return subclassCheckCache.get(other);

        if (this.equals(other)) {
            subclassCheckCache.put(other, true);
            return true;
        }

        if (superClass() != null)
            if (superClass().isSubclassOf(other)) {
                subclassCheckCache.put(other, true);
                return true;
            }

        for (var inter : interfaces()) {
            if (inter.isSubclassOf(other)) {
                subclassCheckCache.put(other, true);
                return true;
            }
        }
        subclassCheckCache.put(other, false);
        return false;
    }

    @Override
    public Set<AccessFlag> accessFlags() {
        return accessFlags;
    }

    @Override
    public GenericArgument[] genericArguments() {
        return genericArguments;
    }

    @Override
    public boolean isInterface() {
        return isInterface;
    }

    @Override
    public boolean isPrimitive() {
        return isPrimitive;
    }

    @Override
    public boolean isAnnotation() {
        return isAnnotation;
    }

    @Override
    public boolean isEnum() {
        return isEnum;
    }

    @Override
    public TypeModel typeModel(int arrayDimensions) {
        if (ModelCache.containsTypeModel(this, arrayDimensions))
            return ModelCache.getTypeModel(this, arrayDimensions);

        TypeModel model;
        if (!isPrimitive())
            model = new TypeModelImpl(this, arrayDimensions);
        else
            model = new PrimitiveTypeModelImpl(this, arrayDimensions);

        ModelCache.cacheTypeModel(model);
        return model;
    }

    @Override
    public ClassDesc classDesc() {
        return typeModel().classDesc();
    }

    @Override
    public ClassDesc classDesc(int arrayDimensions) {
        return typeModel(arrayDimensions).classDesc();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ClassModel) obj;
        return Objects.equals(this.name(), that.name()) &&
                Objects.equals(this.pkg(), that.pkg()) &&
                Objects.equals(this.superClass(), that.superClass()) &&
                Arrays.equals(this.interfaces(), that.interfaces()) &&
                Objects.equals(this.accessFlags(), that.accessFlags()) &&
                Arrays.equals(this.genericArguments(), that.genericArguments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName(), superClass, Arrays.hashCode(interfaces), accessFlags, Arrays.hashCode(genericArguments));
    }

    @Override
    public String toString() {
        return "ClassModel[" +
                "package=" + pkg + ", " +
                "name=" + name + ", " +
                "superClass=" + superClass + ", " +
                "interfaces=" + Arrays.toString(interfaces) + ", " +
                "accessFlags=" + accessFlags + ", " +
                "genericArguments=" + Arrays.toString(genericArguments) + ", " +
                "methods=" + Arrays.toString(methods()) + ", " +
                "fields=" + Arrays.toString(fields()) + ']';
    }
    @Override
    public MethodModel getMethod(String name, TypeModel... parameters) {
        MethodModel candidate = getMethodFromClassHierarchy(this, name, parameters);
        if (candidate != null) {
            return candidate;
        }

        candidate = getMethodFromInterfaces(name, parameters);
        return candidate;
    }

    private MethodModel getMethodFromClassHierarchy(ClassModel startClass, String name, TypeModel... parameters) {
        ClassModel owner = startClass;
        MethodModel candidate = ModelCache.getMethod(owner, name, parameters);

        while (candidate == null && owner.superClass() != null) {
            owner = owner.superClass();
            candidate = ModelCache.getMethod(owner, name, parameters);
        }

        return candidate;
    }

    private MethodModel getMethodFromInterfaces(String name, TypeModel... parameters) {
        for (ClassModel interfaceModel : interfaces()) {
            MethodModel candidate = interfaceModel.getMethod(name, parameters);
            if (candidate != null) {
                return candidate;
            }
        }
        return null;
    }

    @Override
    public MethodModel getMethod(String name, List<TypeModel> parameters) {
        return getMethod(name, parameters.toArray(TypeModel[]::new));
    }

    private void addSuperclassMethods(Set<MethodModel> methods) {
        var sup = superClass();
        if (sup == null) return;

        methods.addAll(List.of(sup.methods()));
    }

    private void addInterfaceMethods(ClassModel inter, Set<MethodModel> methods) {
        if (inter != null)
            methods.addAll(List.of(inter.methods()));
    }

    @Override
    public MethodModel[] methods() {
        Set<MethodModel> models = new HashSet<>();

        if (ModelCache.getModelMethods(this) != null) {
            for (var entry : ModelCache.getModelMethods(this).entrySet()) {
                for (var entry2 : entry.getValue().entrySet()) {
                    models.add(entry2.getValue());
                }
            }
        }
        addSuperclassMethods(models);
        for (var inter : interfaces()) {
            addInterfaceMethods(inter, models);
        }
        return models.toArray(MethodModel[]::new);
    }

    @Override
    public FieldModel getField(String name) {
        return ModelCache.getField(this, name);
    }

    private static final FieldModel[] EMPTY_FIELDS = new FieldModel[0];

    @Override
    public FieldModel[] fields() {
        var fields = ModelCache.getModelFields(this);
        if (fields == null) return EMPTY_FIELDS;
        return fields.values().toArray(FieldModel[]::new);
    }

    @Override
    public AnnotationModel[] annotations() {
        return annotations;
    }

}