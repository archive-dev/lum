package lum.core.impl;

import lum.core.model.ClassModel;
import lum.core.model.TypeArgument;
import lum.core.model.TypeModel;
import org.jetbrains.annotations.NotNull;

import java.lang.constant.ClassDesc;
import java.util.Optional;

record TypeModelImpl(
        ClassModel model,
        int arrayDimensions,
        Optional<TypeArgument[]> genericArguments
) implements TypeModel {

    @Override
    public TypeModel asArray(int arrayDimensions) {
        return new TypeModelImpl(model(), arrayDimensions() + arrayDimensions, genericArguments());
    }

    @Override
    public ClassDesc classDesc() {
        ClassDesc desc = ClassDesc.of(model.name());
        if (arrayDimensions() == 0)
            return desc;
        return desc.arrayType(arrayDimensions());
    }

    @Override
    public @NotNull String toString() {
        return classDesc().toString();
    }
}
