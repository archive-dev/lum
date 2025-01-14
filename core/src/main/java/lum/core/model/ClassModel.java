package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.List;

// This one is abstract because superClass must be accessible only inside lum.core.model
public abstract class ClassModel implements Accessible, GenericTyped {
    public abstract String name();

    abstract ClassModel superClass();
    public abstract void setSuperClass(ClassModel value);

    public abstract ClassModel[] interfaces();

    @Override
    public abstract List<AccessFlag> accessFlags();

    @Override
    public abstract GenericParameter[] genericParameters();

    public abstract TypeModel typeModel();

    public abstract boolean isInterface();

    public abstract TypeModel typeModel(int arrayDimensions);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
