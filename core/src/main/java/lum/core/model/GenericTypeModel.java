package lum.core.model;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;

public record GenericTypeModel(
    String name,
    ClassModel model,
    int arrayDimensions,
    GenericArgument[] genericArguments
) implements TypeModel {
    @Override
    public TypeKind typeKind() {
        return model().typeModel().typeKind();
    }

    @Override
    public ClassDesc classDesc() {
        return model().classDesc();
    }

    @Override
    public TypeModel asComponent() {
        return new GenericTypeModel(name(), model(), 0, genericArguments());
    }

    @Override
    public TypeModel asNonArray() {
        return new GenericTypeModel(name(), model(), 0, genericArguments());
    }

    @Override
    public TypeModel asArray(int dimensions) {
        return new GenericTypeModel(name(), model(), arrayDimensions()+dimensions, genericArguments());
    }
}
