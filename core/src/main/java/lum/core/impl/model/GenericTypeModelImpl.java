package lum.core.impl.model;

import lum.core.model.*;
import lum.lang.struct.Either;

import java.lang.constant.ClassDesc;
import java.util.Optional;

public record GenericTypeModelImpl(
        Either<ClassModel, MethodModel> declarator,
        TypeParameter parameter,
        ClassModel model,
        int arrayDimensions
) implements TypeModel.GenericTypeModel {
    @Override
    public Optional<TypeArgument[]> genericArguments() {
        return Optional.empty();
    }

    @Override
    public TypeModel asArray(int arrayDimensions) {
        return new GenericTypeModelImpl(declarator, parameter, model, this.arrayDimensions+arrayDimensions);
    }

    @Override
    public ClassDesc classDesc() {
        return parameter.bound().classDesc();
    }
}
