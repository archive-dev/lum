package lum.core.model;

import lum.core.util.Utils;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;

record IntersectionTypeModel(
        IntersectionClassModel model,
        int arrayDimensions
) implements TypeModel {
    @Override
    public TypeModel asArray(int dimensions) {
        return new IntersectionTypeModel(model(), arrayDimensions()+dimensions);
    }

    @Override
    public TypeModel asComponent() {
        return new IntersectionTypeModel(model(), arrayDimensions()-1);
    }

    @Override
    public TypeModel asNonArray() {
        return new IntersectionTypeModel(model(), 0);
    }

    @Override
    public ClassDesc classDesc() {
        return model().classDesc();
    }

    @Override
    public TypeKind typeKind() {
        return TypeKind.REFERENCE;
    }

    @Override
    public GenericArgument[] genericArguments() {
        return Utils.EMPTY_GENERIC_ARGUMENTS;
    }
}
