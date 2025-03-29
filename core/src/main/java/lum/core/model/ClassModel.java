package lum.core.model;

import org.jetbrains.annotations.Nullable;

import java.lang.constant.ClassDesc;
import java.util.List;

public abstract class ClassModel implements Accessible, GenericTyped, Annotatable {
    public abstract String name();
    public abstract String pkg();

    public abstract ClassModel superClass();
    abstract void setSuperClass(ClassModel value);

    public abstract ClassModel[] interfaces();

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

    public abstract @Nullable MethodModel getMethod(String name, TypeModel... parameters);

    protected abstract MethodModel getMethodFromClassHierarchy(ClassModel startClass, String name, TypeModel... parameters);

    protected abstract MethodModel getMethodFromInterfaces(String name, TypeModel... parameters);

    public abstract MethodModel getMethod(String name, List<TypeModel> parameters);

    public abstract MethodModel[] methods();

    public abstract FieldModel getField(String name);

    public abstract FieldModel[] fields();

    public static ClassModel of(Class<?> clazz) {
        return ModelCache.cacheClass(ModelCache.getClass(clazz));
    }
}
