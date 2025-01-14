package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.List;

public interface FieldModel extends Accessible, GenericTyped, Member {
    ClassModel owner();

    String name();

    TypeModel type();

    List<AccessFlag> accessFlags();

    GenericParameter[] genericParameters();
}
