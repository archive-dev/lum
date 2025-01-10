package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.List;

public record MethodModel (
        ClassModel owner,
        String name,
        TypeModel returnType,
        ParameterModel[] parameters,
        TypeModel[] exceptions,
        List<AccessFlag> accessFlags,
        GenericParameter[] genericParameters
) implements Accessible, GenericTyped, Member {}
