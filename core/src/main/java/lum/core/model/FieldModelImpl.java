package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.List;
import java.util.Set;

record FieldModelImpl(
        ClassModel owner,
        String name,
        TypeModel type,
        Set<AccessFlag> accessFlags,
        GenericParameter[] genericParameters,
        ClassModel[] annotations
) implements FieldModel {
    @Override
    public String toString() {
        return "FieldModelImpl[" +
                "name=" + name + ", " +
                "type=" + type + ", " +
                "accessFlags=" + accessFlags + ", " +
                "genericParameters=" + GenericParameter.toString(genericParameters) + ']';
    }
}
