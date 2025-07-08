package lum.core.model;

import lum.core.impl.model.GenericTypeModelImpl;

import java.lang.classfile.Signature;

public interface TypeParameter {
    String name();
    TypeModel bound();
    default TypeModel.GenericTypeModel asGenericTypeModel(Member member) {
        return switch (member) {
            case MethodModel method -> asGenericTypeModel(method); 
            case ClassModel model -> asGenericTypeModel(model);
            default -> throw new IllegalStateException("Unexpected value: " + member);
        };
    }
    TypeModel.GenericTypeModel asGenericTypeModel(MethodModel method);
    TypeModel.GenericTypeModel asGenericTypeModel(ClassModel model);
    Signature.TypeArg.Bounded.WildcardIndicator wildcardType();
}
