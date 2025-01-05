package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record FieldModel(
        ClassModel owner,
        String name,
        ClassModel type,
        AccessFlag[] accessFlags,
        List<AttributeModel> attributes
) {
    public static FieldModel from(Field field) {
        ClassModel owner = ClassModelPool.get(field.getDeclaringClass());
        String name = field.getName();
        ClassModel type = ClassModelPool.get(field.getType());
        AccessFlag[] accessFlags = field.accessFlags().toArray(AccessFlag[]::new);
        List<AttributeModel> attributes = List.of();

        return new FieldModel(owner, name, type, accessFlags, attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner().name(), name, type().name(), Arrays.hashCode(accessFlags()), attributes());
    }
}
