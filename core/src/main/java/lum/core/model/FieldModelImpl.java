package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.Set;

record FieldModelImpl(
        ClassModel owner,
        String name,
        TypeModel type,
        Set<AccessFlag> accessFlags,
        AnnotationModel[] annotations
) implements FieldModel {
    @Override
    public String toString() {
        return "FieldModelImpl[" +
                "name=" + name + ", " +
                "type=" + type + ", " +
                "accessFlags=" + accessFlags + ']';
    }
}
