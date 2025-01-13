package lum.core.model;

import java.lang.constant.ClassDesc;

public record TypeModel(
        ClassModel model,
        int arrayDimensions
) {
    public TypeModel asArray(int dimensions) {
        return new TypeModel(model(), arrayDimensions()+dimensions);
    }

    public ClassDesc classDesc() {
        ClassDesc classDesc = ClassDesc.of(model().name());
        return classDesc.arrayType(arrayDimensions());
    }
}
