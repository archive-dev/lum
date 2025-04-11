package lum.core.model;

import lum.core.util.Utils;

public interface FieldModel extends Accessible, Member {
    String name();

    TypeModel type();

    String toString();

    default MethodModel getGetter() {
        return owner().getMethod("get"+ Utils.toTitled(name()));
    }

    default MethodModel getSetter(TypeModel parameterType) {
        return owner().getMethod("set"+Utils.toTitled(name()), parameterType);
    }
}
