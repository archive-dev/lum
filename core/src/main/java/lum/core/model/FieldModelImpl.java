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
}
