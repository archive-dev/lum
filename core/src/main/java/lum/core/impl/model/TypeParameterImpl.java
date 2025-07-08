package lum.core.impl.model;

import lum.core.model.ClassModel;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;
import lum.core.model.TypeParameter;
import lum.lang.struct.Either;

import java.lang.classfile.Signature;

public record TypeParameterImpl(
        String name,
        TypeModel bound,
        Signature.TypeArg.Bounded.WildcardIndicator wildcardType
) implements TypeParameter {
    @Override
    public TypeModel.GenericTypeModel asGenericTypeModel(MethodModel method) {
        return new GenericTypeModelImpl(Either.right(method), this, bound.model(), 0);
    }

    @Override
    public TypeModel.GenericTypeModel asGenericTypeModel(ClassModel model) {
        return new GenericTypeModelImpl(Either.left(model), this, bound.model(), 0);
    }
}
