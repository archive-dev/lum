package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.List;

public interface MethodModel extends Accessible, GenericTyped, Member {
    ClassModel owner();

    String name();

    TypeModel returnType();

    ParameterModel[] parameters();

    TypeModel[] exceptions();

    List<AccessFlag> accessFlags();

    GenericParameter[] genericParameters();
}
