package lum.core.model;

import java.lang.constant.ClassDesc;

public record TypeModel(
        ClassModel model,
        int arrayDepth
) {
    public ClassDesc classDesc() {
        ClassDesc classDesc = ClassDesc.of(model().name());
        return classDesc.arrayType(arrayDepth());
    }
}
