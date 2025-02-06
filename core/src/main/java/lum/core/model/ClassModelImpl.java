package lum.core.model;

import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

final class ClassModelImpl extends ClassModel {
    private final String name;
    ClassModel superClass;
    private final ClassModel[] interfaces;
    private final List<AccessFlag> accessFlags;
    private final GenericParameter[] genericParameters;
    private final boolean isInterface;

    public ClassModelImpl(
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

    @Override
    public String name() {
        return name;
    }

    @Override
    public ClassModel superClass() {
        return superClass;
    }

    @Override
    public void setSuperClass(ClassModel value) {
        superClass = value;
    }

    @Override
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

    @Override
    public boolean isInterface() {
        return isInterface;
    }

    private final HashMap<ClassModel, Boolean> subclassCheckCache = new HashMap<>();

    @Override
    public boolean isSubclassOf(ClassModel other) {
        if (subclassCheckCache.containsKey(other))
            return subclassCheckCache.get(other);

        ClassModel superClass = superClass();
        while (superClass != ClassModel.of(Object.class) || superClass != null) {
            if (other.equals(superClass)) {
                subclassCheckCache.put(other, true);
                return true;
            }
            superClass = superClass();
        }

        subclassCheckCache.put(other, false);
        return false;
    }

    @Override
    public TypeModel typeModel() {
        return new TypeModelImpl(this, 0);
    }

    @Override
    public TypeModel typeModel(int arrayDimensions) {
        return new TypeModelImpl(this, arrayDimensions);
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
        return Objects.equals(this.name, that.name()) &&
                Objects.equals(this.superClass, that.superClass()) &&
                Arrays.equals(this.interfaces, that.interfaces()) &&
                Objects.equals(this.accessFlags, that.accessFlags()) &&
                Arrays.equals(this.genericParameters, that.genericParameters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, superClass, Arrays.hashCode(interfaces), accessFlags, Arrays.hashCode(genericParameters));
    }

    @Override
    public String toString() {
        return "ClassModel[" +
                "name=" + name + ", " +
                "superClass=" + superClass + ", " +
                "interfaces=" + Arrays.toString(interfaces) + ", " +
                "accessFlags=" + accessFlags + ", " +
                "genericParameters=" + Arrays.toString(genericParameters) + ", " +
                "methods=" + Arrays.toString(methods()) + ", " +
                "fields=" + Arrays.toString(fields()) + ']';
    }
}