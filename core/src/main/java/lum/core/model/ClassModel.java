package lum.core.model;

import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// This one is abstract because superClass must be accessible only inside lum.core.model
public abstract class ClassModel implements Accessible, GenericTyped {
    private HashSet<MethodModel> methods = null;
    private HashSet<FieldModel> fields = null;
    
    public abstract String name();

    abstract ClassModel superClass();
    public abstract void setSuperClass(ClassModel value);

    public abstract ClassModel[] interfaces();

    @Override
    public abstract List<AccessFlag> accessFlags();

    @Override
    public abstract GenericParameter[] genericParameters();

    public abstract boolean isInterface();

    public abstract TypeModel typeModel();

    public abstract TypeModel typeModel(int arrayDimensions);

    public abstract ClassDesc classDesc();

    public abstract ClassDesc classDesc(int arrayDimensions);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    public MethodModel getMethod(String name, TypeModel... parameters) {
        ClassModel owner = this;
        MethodModel candidate = ModelCache.getMethod(owner, name, parameters);
        while (candidate == null && owner.superClass() != null) {
            owner = owner.superClass();
            candidate = ModelCache.getMethod(owner, name, parameters);
        }

        if (candidate != null) return candidate;

        for (ClassModel interfaceModel : interfaces()) {
            candidate = interfaceModel.getMethod(name, parameters);
            if (candidate != null) break;
        }

        return candidate;
    }

    public MethodModel getMethod(String name, List<TypeModel> parameters) {
        return getMethod(name, parameters.toArray(TypeModel[]::new));
    }

    public MethodModel[] methods() {
        if (methods != null) return methods.toArray(MethodModel[]::new);
        if (!ModelCache.classModelMethods.containsKey(this)) return new MethodModel[0];

        methods = new HashSet<>();

        for (var entry : ModelCache.classModelMethods.get(this).entrySet()) {
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
        if (!ModelCache.classModelFields.containsKey(this)) return new FieldModel[0];

        fields = new HashSet<>();

        for (Map.Entry<String, FieldModel> entry : ModelCache.classModelFields.get(this).entrySet()) {
            fields.add(entry.getValue());
        }

        return fields.toArray(FieldModel[]::new);
    }

    public static ClassModel of(Class<?> clazz) {
        return ModelCache.getClass(clazz);
    }
}
