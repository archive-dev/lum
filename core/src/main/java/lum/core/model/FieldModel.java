package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.List;

public record FieldModel(
        ClassModel owner,
        String name,
        TypeModel type,
        List<AccessFlag> accessFlags,
        GenericParameter[] genericParameters
) implements Accessible, GenericTyped, Member {
}
