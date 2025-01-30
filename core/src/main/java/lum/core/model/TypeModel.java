package lum.core.model;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;

public interface TypeModel extends GenericTyped {
    TypeModel asArray(int dimensions);

    ClassDesc classDesc();

    TypeKind typeKind();

    ClassModel model();

    int arrayDimensions();

    static TypeModel of(Class<?> clazz) {
        return ModelCache.getTypeModel(clazz);
    }
}
