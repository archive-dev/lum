package lum.core.impl.model.factory;

import lum.core.impl.model.FieldModelImpl;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.TypeModel;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;

/**
 * Factory for creating FieldModel instances from Java reflection objects.
 */
public class FieldModelFactory {
    
    /**
     * Creates a FieldModel from a Java Field.
     */
    public FieldModel createFromField(Field field) {
        return new FieldModelImpl(
                ClassModel.of(field.getDeclaringClass()),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                field.accessFlags().toArray(AccessFlag[]::new),
                field.getName(),
                TypeModel.of(field.getType()).orElseThrow()
        );
    }
}