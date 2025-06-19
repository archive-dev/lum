package lum.core.impl;

import lum.core.model.FieldModel;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;

final class FieldModelFactory {
    private FieldModelFactory() {}

    public static FieldModel of(Field field) {
        return new FieldModelImpl(
                ClassModelFactory.of(field.getDeclaringClass()),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                field.accessFlags().toArray(AccessFlag[]::new),
                field.getName(),
                null
        );
    }
}
