package lum.core.model;

import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// This one is abstract because superClass must be accessible only inside lum.core.model
public abstract class ClassModel implements Accessible, GenericTyped { // разобратсья почему cacheClassMembers не вызывается (на ArrayList)
    private HashSet<MethodModel> methods = null;
    private HashSet<FieldModel> fields = null;
    
    public abstract String name();

    public abstract ClassModel superClass();
    abstract void setSuperClass(ClassModel value);

    public abstract ClassModel[] interfaces();

    @Override
    public abstract GenericParameter[] genericParameters();

    public abstract boolean isSubclassOf(ClassModel other);

    public abstract boolean isInterface();

    public abstract boolean isPrimitive();

    public abstract TypeModel typeModel();

    public abstract TypeModel typeModel(int arrayDimensions);

    public abstract ClassDesc classDesc();

    public abstract ClassDesc classDesc(int arrayDimensions);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

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

    public MethodModel getMethod(String name, List<TypeModel> parameters) {
        return getMethod(name, parameters.toArray(TypeModel[]::new));
    }

    public MethodModel[] methods() {
        if (methods != null) return methods.toArray(MethodModel[]::new);
        if (!ModelCache.modelContainsMethods(this)) return new MethodModel[0];

        methods = new HashSet<>();

        for (var entry : ModelCache.getModelMethods(this).entrySet()) {
            methods.addAll(entry.getValue().values());
        }

        return methods.toArray(MethodModel[]::new);
    }

    public FieldModel getField(String name) {
        ClassModel owner = this;
        FieldModel candidate = ModelCache.getField(owner, name);
        while (candidate == null && owner.superClass() != null) {
            owner = owner.superClass();
            candidate = ModelCache.getField(owner, name);
        }

        return candidate;
    }

    public FieldModel[] fields() {
        if (fields != null) return fields.toArray(FieldModel[]::new);
        if (!ModelCache.modelContainsFields(this)) return new FieldModel[0];

        fields = new HashSet<>();

        for (Map.Entry<String, FieldModel> entry : ModelCache.getModelFields(this).entrySet()) {
            fields.add(entry.getValue());
        }

        return fields.toArray(FieldModel[]::new);
    }

    public static ClassModel of(Class<?> clazz) {
        return ModelCache.cacheClass(ModelCache.getClass(clazz));
    }
}
