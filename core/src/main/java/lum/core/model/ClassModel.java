package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.List;
import java.util.Objects;

public final class ClassModel implements Accessible, GenericTyped {
    private final String name;
    ClassModel superClass;
    private final ClassModel[] interfaces;
    private final List<AccessFlag> accessFlags;
    private final GenericParameter[] genericParameters;
    private final boolean isInterface;

    public ClassModel(
            String name,
            ClassModel superClass,
            ClassModel[] interfaces,
            List<AccessFlag> accessFlags,
            GenericParameter[] genericParameters,
            boolean isInterface
    ) {
        this.name = name;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.accessFlags = accessFlags;
        this.genericParameters = genericParameters;
        this.isInterface = isInterface;
    }

    public String name() {
        return name;
    }

    public ClassModel superClass() {
        return superClass;
    }

    public ClassModel[] interfaces() {
        return interfaces;
    }

    @Override
    public List<AccessFlag> accessFlags() {
        return accessFlags;
    }

    @Override
    public GenericParameter[] genericParameters() {
        return genericParameters;
    }

    public TypeModel typeModel() {
        return new TypeModel(this, 0);
    }

    public boolean isInterface() {
        return isInterface;
    }

    public TypeModel typeModel(int arrayDimensions) {
        return new TypeModel(this, arrayDimensions);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ClassModel) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.superClass, that.superClass) &&
                Objects.equals(this.interfaces, that.interfaces) &&
                Objects.equals(this.accessFlags, that.accessFlags) &&
                Objects.equals(this.genericParameters, that.genericParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, superClass, interfaces, accessFlags, genericParameters);
    }

    @Override
    public String toString() {
        return "ClassModel[" +
                "name=" + name + ", " +
                "superClass=" + superClass + ", " +
                "interfaces=" + interfaces + ", " +
                "accessFlags=" + accessFlags + ", " +
                "genericParameters=" + genericParameters + ']';
    }
}