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
    public ClassDesc classDesc() {
        ClassDesc classDesc = ClassDesc.of(model().name());
        return classDesc.arrayType(arrayDimensions());
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
