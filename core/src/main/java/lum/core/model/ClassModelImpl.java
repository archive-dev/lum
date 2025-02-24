package lum.core.model;

import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.util.*;

final class ClassModelImpl extends ClassModel {
    private final String name;
    ClassModel superClass;
    private final ClassModel[] interfaces;
    private final Set<AccessFlag> accessFlags;
    private final GenericParameter[] genericParameters;
    private final boolean isInterface;
    private final boolean isPrimitive;

    public ClassModelImpl(
            String name,
            ClassModel superClass,
            ClassModel[] interfaces,
            Set<AccessFlag> accessFlags,
            GenericParameter[] genericParameters,
            boolean isInterface, boolean isPrimitive
    ) {
        this.name = name;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.accessFlags = accessFlags;
        this.genericParameters = genericParameters;
        this.isInterface = isInterface;
        this.isPrimitive = isPrimitive;
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
    public GenericParameter[] genericParameters() {
        return genericParameters;
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
    public TypeModel typeModel() {
        return typeModel(0);
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