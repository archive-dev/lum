package lum.core.model;

import java.lang.constant.ClassDesc;

record TypeModelImpl(
        ClassModelImpl model,
        int arrayDimensions
) implements TypeModel {
    @Override
    public TypeModel asArray(int dimensions) {
        return new TypeModelImpl(model(), arrayDimensions()+dimensions);
    }

    @Override
    public ClassDesc classDesc() {
        ClassDesc classDesc = ClassDesc.of(model().name());
        return classDesc.arrayType(arrayDimensions());
    }
}
