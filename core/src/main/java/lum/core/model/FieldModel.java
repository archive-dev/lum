package lum.core.model;

import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.util.List;

public interface FieldModel extends Accessible, GenericTyped, Member {
    String name();

    TypeModel type();

    List<AccessFlag> accessFlags();

    String toString();

    default MethodModel getGetter() {
        return owner().getMethod("get"+ Utils.toTitled(name()));
    }

    default MethodModel getSetter(TypeModel parameterType) {
        return owner().getMethod("set"+Utils.toTitled(name()), parameterType);
    }
}
