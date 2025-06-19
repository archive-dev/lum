package lum.core.model;

import java.lang.constant.ClassDesc;
import java.util.Optional;

public interface TypeModel {
    ClassModel model();
    int arrayDimensions();
    Optional<TypeArgument[]> genericArguments();

    TypeModel asArray(int arrayDimensions);

    ClassDesc classDesc();

    default boolean isArray() {
        return arrayDimensions() != 0;
    }

    interface PrimitiveTypeModel extends TypeModel {}
    interface UnionTypeModel extends TypeModel {

        TypeModel[] getTypes();

        TypeModel lubTypeModel();
    }
    interface IntersectionTypeModel extends TypeModel {

        TypeModel primaryTypeModel();

        TypeModel[] getTypes();

        boolean isSatisfiedBy(TypeModel type);
    }
}
