package lum.core.model;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;

import static lum.core.util.Utils.EMPTY_GENERIC_PARAMETERS;

record TypeModelImpl(
        ClassModel model,
        int arrayDimensions,
        GenericParameter[] genericParameters
) implements TypeModel {
    public TypeModelImpl(ClassModel model, int arrayDimensions) {
        this(model, arrayDimensions, EMPTY_GENERIC_PARAMETERS);
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
        return model().name() + "[]".repeat(arrayDimensions());
    }
}
