package lum.core.model;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;

public interface TypeModel extends GenericTyped {
    TypeModel VOID = TypeModel.of(void.class);
    TypeModel BOOLEAN = TypeModel.of(boolean.class);
    TypeModel BYTE = TypeModel.of(byte.class);
    TypeModel SHORT = TypeModel.of(short.class);
    TypeModel CHAR = TypeModel.of(char.class);
    TypeModel INT = TypeModel.of(int.class);
    TypeModel LONG = TypeModel.of(long.class);
    TypeModel DOUBLE = TypeModel.of(double.class);
    TypeModel FLOAT = TypeModel.of(float.class);
    TypeModel STRING = TypeModel.of(String.class);
    TypeModel OBJECT = TypeModel.of(Object.class);

    TypeModel asArray(int dimensions);

    TypeModel asComponent();

    ClassDesc classDesc();

    TypeKind typeKind();

    ClassModel model();

    int arrayDimensions();

    static TypeModel of(Object value) {
        return switch (value) {
            case Boolean _ -> TypeModel.BOOLEAN;
            case Byte _ -> TypeModel.BYTE;
            case Short _ -> TypeModel.SHORT;
            case Character _ -> TypeModel.CHAR;
            case Integer _ -> TypeModel.INT;
            case Long _ -> TypeModel.LONG;
            case Double _ -> TypeModel.DOUBLE;
            case Float _ -> TypeModel.FLOAT;
            case String _ -> TypeModel.STRING;
            case null -> TypeModel.OBJECT;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    static TypeModel of(TypeKind typeKind) {
        return switch (typeKind) {
            case ByteType -> TypeModel.BYTE;
            case ShortType -> TypeModel.SHORT;
            case IntType -> TypeModel.INT;
            case FloatType -> TypeModel.FLOAT;
            case LongType -> TypeModel.LONG;
            case DoubleType -> TypeModel.DOUBLE;
            case ReferenceType -> TypeModel.OBJECT;
            case CharType -> TypeModel.CHAR;
            case BooleanType -> TypeModel.BOOLEAN;
            case VoidType -> TypeModel.VOID;
            case null -> null;
        };
    }

    static TypeModel of(Class<?> clazz) {
        return ModelCache.getTypeModel(clazz);
    }

    static TypeModel of(String className) {
        try {
            return of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    default boolean isArray() {
        return arrayDimensions()!=0;
    }

    default boolean isPrimitive() {
        return false;
    }

}
