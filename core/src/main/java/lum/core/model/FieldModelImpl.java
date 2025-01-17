package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.List;

record FieldModelImpl(
        ClassModel owner,
        String name,
        TypeModel type,
        List<AccessFlag> accessFlags,
        GenericParameter[] genericParameters
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
