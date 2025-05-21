package lum.core.model;

import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.constant.ClassDesc;
import java.util.List;

public abstract class ClassModel implements Accessible, Parametrized, Annotatable {
    public static final ClassModel INT = ClassModel.of(int.class);
    public static final ClassModel LONG = ClassModel.of(long.class);
    public static final ClassModel DOUBLE = ClassModel.of(double.class);
    public static final ClassModel FLOAT = ClassModel.of(float.class);
    public static final ClassModel BOOLEAN = ClassModel.of(boolean.class);
    public static final ClassModel BYTE = ClassModel.of(byte.class);
    public static final ClassModel CHAR = ClassModel.of(char.class);
    public static final ClassModel SHORT = ClassModel.of(short.class);
    public static final ClassModel VOID = ClassModel.of(void.class);
    public static final ClassModel OBJECT = ClassModel.of(Object.class);
    public static final ClassModel ANNOTATION = ClassModel.of(Annotation.class);

    public abstract String name();
    public abstract String pkg();
    public String fullName() {
        if (pkg() != null && !pkg().isEmpty()) return ("%s.%s").formatted(pkg(), name());
        return name();
    }

    public abstract ClassModel superClass();
    void setSuperClass(ClassModel value) {};

    public abstract ClassModel[] interfaces();

    public abstract FieldModel[] fields();

    public abstract MethodModel[] methods();

    public abstract boolean isInterface();

    public abstract boolean isPrimitive();

    public abstract boolean isAnnotation();

    public abstract boolean isEnum();

    public abstract boolean isSubclassOf(ClassModel other);

    public boolean isAssignableFrom(ClassModel other) {
        return other.isSubclassOf(this);
    }

    public TypeModel typeModel() {
        return typeModel(0);
    }

    public abstract TypeModel typeModel(int arrayDimensions);

    public abstract ClassDesc classDesc();

    public abstract ClassDesc classDesc(int arrayDimensions);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    public abstract @Nullable MethodModel getMethod(String name, TypeModel... parameters);

//    protected abstract MethodModel getMethodFromClassHierarchy(ClassModel startClass, String name, TypeModel... parameters);

//    protected abstract MethodModel getMethodFromInterfaces(String name, TypeModel... parameters);

    public abstract MethodModel getMethod(String name, List<TypeModel> parameters);

    public abstract FieldModel getField(String name);

    public static ClassModel of(Class<?> clazz) {
        return ModelCache.cacheClass(ModelCache.getClass(clazz));
    }
//
//    public static ClassModel of(AccessFlag[] accessFlags, String name, ClassModel superClass, ClassModel[] interfaces, FieldModel[] fields, MethodModel[] methods) {
//        final var nameList = List.of(name.split("\\."));
//        final String className = nameList.getLast();
//        nameList.removeLast();
//        final String pkg = String.join(".", nameList);
//
//        return new ClassModelImpl(
//                className,
//                pkg,
//                superClass,
//                interfaces,
//                Set.of(accessFlags),
//                Utils.EMPTY_GENERIC_ARGUMENTS,
//                Utils.EMPTY_CLASS_MODELS,
//                false,
//                false
//        );
//    }
}
