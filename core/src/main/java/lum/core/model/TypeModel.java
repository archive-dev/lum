package lum.core.model;

import lum.core.impl.model.ClassModelFactory;
import lum.core.util.Utils;

import java.lang.classfile.TypeKind;
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

    default boolean isPrimitive() {
        return model().isPrimitive() && !isArray();
    }

    default TypeKind typeKind() {
        if (!isPrimitive() || arrayDimensions() != 0)
            return TypeKind.REFERENCE;

        return switch (model().className()) {
            case "int" -> TypeKind.INT;
            case "long" -> TypeKind.LONG;
            case "byte" -> TypeKind.BYTE;
            case "short" -> TypeKind.SHORT;
            case "double" -> TypeKind.DOUBLE;
            case "float" -> TypeKind.FLOAT;
            case "char" -> TypeKind.CHAR;
            case "void" -> TypeKind.VOID;
            default -> throw new IllegalStateException("Unexpected value: " + model().className());
        };
    }

    default TypeModel asComponent() {
        return model().asTypeModel(
                arrayDimensions()-1,
                genericArguments().orElse(Utils.EMPTY_TYPE_ARGUMENTS)
        );
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

    /// Creates a [TypeModel] from given [Class]
    /// @param clazz the {@link Class} to create a model for, may be null
    /// @return returns an {@link Optional} of TypeModel or empty
    /// if clazz is null or there was an error while creating [TypeModel]
    static Optional<TypeModel> of(Class<?> clazz) {
        return ClassModelFactory.typeModelOf(clazz);
    }
}
