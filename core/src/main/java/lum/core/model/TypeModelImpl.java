package lum.core.model;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;

import static lum.core.util.Utils.EMPTY_GENERIC_ARGUMENTS;

record TypeModelImpl(
        ClassModel model,
        int arrayDimensions,
        GenericArgument[] genericArguments
) implements TypeModel {
    public TypeModelImpl(ClassModel model, int arrayDimensions) {
        this(model, arrayDimensions, EMPTY_GENERIC_ARGUMENTS);
    }

    @Override
    public TypeModel asArray(int dimensions) {
        return new TypeModelImpl(model(), arrayDimensions()+dimensions);
    }

    @Override
    public TypeModel asComponent() {
        if (!isArray())
            return this;
        return new TypeModelImpl(model(), arrayDimensions()-1);
    }

    @Override
    public TypeModel asNonArray() {
        return new TypeModelImpl(model(), 0, genericArguments());
    }

    @Override
    public ClassDesc classDesc() {
        ClassDesc classDesc = ClassDesc.of("%s%s".formatted(model().pkg().isEmpty() ? "" : model().pkg() + ".", model().name()));
        if (arrayDimensions() > 0)
            return classDesc.arrayType(arrayDimensions());
        return classDesc;
    }

    @Override
    public TypeKind typeKind() {
        return TypeKind.fromDescriptor(classDesc().descriptorString());
    }

    @Override
    public String toString() {
        return model().name() + (genericArguments().length == 0 ? "" : GenericArgument.toString(genericArguments())) + "[]".repeat(arrayDimensions());
    }
}
