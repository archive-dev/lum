package lum.core.model;

import lum.core.impl.model.ClassModelFactory;
import lum.core.impl.model.IntersectionClassModelImpl;
import lum.core.impl.model.IntersectionTypeModelImpl;
import lum.core.impl.model.UnionTypeModelImpl;
import lum.core.util.Utils;
import lum.lang.struct.Either;
import org.jetbrains.annotations.NotNull;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;
import java.util.Arrays;
import java.util.Optional;

public interface TypeModel {
    TypeModel INT = TypeModel.of(int.class).orElseThrow();
    TypeModel LONG = TypeModel.of(long.class).orElseThrow();
    TypeModel FLOAT = TypeModel.of(float.class).orElseThrow();
    TypeModel DOUBLE = TypeModel.of(double.class).orElseThrow();
    TypeModel BYTE = TypeModel.of(byte.class).orElseThrow();
    TypeModel SHORT = TypeModel.of(short.class).orElseThrow();
    TypeModel CHAR = TypeModel.of(char.class).orElseThrow();
    TypeModel STRING = TypeModel.of(String.class).orElseThrow();
    TypeModel OBJECT = TypeModel.of(Object.class).orElseThrow();
    TypeModel BOOLEAN = TypeModel.of(boolean.class).orElseThrow();
    TypeModel VOID = TypeModel.of(void.class).orElseThrow();

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

    default TypeModel withTypeArguments(TypeArgument[] typeArguments) {
        return model().asTypeModel(0, typeArguments);
    }

    interface GenericTypeModel extends TypeModel {
        Either<ClassModel, MethodModel> declarator();
        TypeParameter parameter(); // used to bind with TypeParameters
        default ClassModel model() {
            return ClassModel.of(Object.class).orElseThrow();
        }
        default MethodModel method() {
            return declarator().isRight() ? declarator().right() : null;
        }

        default ClassModel class_() {
            return declarator().isLeft() ? declarator().left() : null;
        }
    }

    interface PrimitiveTypeModel extends TypeModel {}
    interface UnionTypeModel extends TypeModel {

        TypeModel[] getTypes();

        TypeModel lubTypeModel();

        static UnionTypeModel of(TypeModel[] typeModels) {
            return new UnionTypeModelImpl(typeModels);
        }

        static UnionTypeModel of(Class<?>[] classes) {
            return new UnionTypeModelImpl(Arrays.stream(classes).map(TypeModel::of).map(Optional::orElseThrow).toArray(TypeModel[]::new));
        }
    }

    interface IntersectionTypeModel extends TypeModel {
        static IntersectionTypeModel of(TypeModel[] typeModels) {
            return new IntersectionTypeModelImpl(typeModels);
        }

        static IntersectionTypeModel of(Class<?>[] classes) {
            return new IntersectionTypeModelImpl(Arrays.stream(classes).map(TypeModel::of).map(Optional::orElseThrow).toArray(TypeModel[]::new));
        }

        TypeModel primaryTypeModel();

        TypeModel[] getTypes();

        boolean isSatisfiedBy(TypeModel type);
    }

    static TypeModel leastUpperBoundOf(@NotNull TypeModel @NotNull [] typeModels) {
        return UnionTypeModel.of(typeModels);
    }

    /// Creates a [TypeModel] from given [Class]
    /// @param clazz the {@link Class} to create a model for, may be null
    /// @return returns an {@link Optional} of TypeModel or empty
    /// if clazz is null or there was an error while creating [TypeModel]
    static Optional<TypeModel> of(Class<?> clazz) {
        return ClassModelFactory.typeModelOf(clazz);
    }

    /// Creates a [TypeModel] from given [Class]
    /// @param clazz the {@link Class} to create a model for, may be null
    /// @param typeArguments the type arguments for given generic class
    /// @return returns an {@link Optional} of TypeModel or empty
    /// if clazz is null or there was an error while creating [TypeModel]
    static Optional<TypeModel> of(Class<?> clazz, TypeModel... typeArguments) {
        return ClassModel.of(clazz)
                .map(cm -> cm.asTypeModel(typeArguments));
    }

    /// Creates a [TypeModel] from given [Class]
    /// @param clazz the {@link Class} to create a model for, may be null
    /// @param typeArguments the type arguments for given generic class
    /// @return returns an {@link Optional} of TypeModel or empty
    /// if clazz is null or there was an error while creating [TypeModel]
    static Optional<TypeModel> of(Class<?> clazz, Class<?>... typeArguments) {
        return ClassModel.of(clazz)
                .map(cm -> cm.asTypeModel(
                        Arrays.stream(typeArguments)
                                .map(TypeModel::of)
                                .map(Optional::orElseThrow)
                                .toArray(TypeModel[]::new)
                ));
    }
}
