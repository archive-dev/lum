package lum.core.impl.model;

import lum.core.model.AttributeModel;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.TypeModel;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.AccessFlag;
import java.util.Arrays;
import java.util.Optional;

public record FieldModelImpl(
        Optional<ClassModel> owner,
        AttributeModel[] attributes,
        AccessFlag[] accessFlags,
        String name,
        TypeModel type
) implements FieldModel {
    @Override
    public @NotNull String toString() {
        return "FieldModelImpl{" +
                "attributes=" + Arrays.toString(attributes) +
                ", accessFlags=" + Arrays.toString(accessFlags) +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
