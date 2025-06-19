package lum.core.model;

import lum.core.util.Utils;

import java.util.Arrays;
import java.util.Optional;

public interface ClassModel extends Attributable, Member, Generic {
//    String name(); // includes package
    default String className() {
        return Arrays.asList(name().split("\\.")).getLast();
    }
    Optional<ClassModel> superClass();
    ClassModel[] interfaces();

    Member[] members();
    Member[] allMembers();

    Optional<MethodModel> getMethod(String name, TypeModel[] parameterTypes);
    default Optional<MethodModel> getMethod(String name) {
        return getMethod(name, Utils.EMPTY_TYPE_MODELS);
    }

    Optional<FieldModel> getField(String name);

    default TypeModel asTypeModel() {
        return asTypeModel(Utils.EMPTY_TYPE_ARGUMENTS);
    }

    default TypeModel asTypeModel(TypeArgument... typeArguments) {
        return asTypeModel(0, typeArguments);
    }

    TypeModel asTypeModel(int arrayDimensions, TypeArgument... typeArguments);
    default TypeModel asTypeModel(TypeModel... typeArguments) {
        return asTypeModel(0, typeArguments);
    }
    TypeModel asTypeModel(int arrayDimensions, TypeModel... typeArguments);

    boolean isSubclassOf(ClassModel other);
    default boolean isAssignableFrom(ClassModel other) {
        return other.isSubclassOf(this);
    }

    String toPrettyString();

    default boolean isPrimitive() {
        return false;
    }
}
