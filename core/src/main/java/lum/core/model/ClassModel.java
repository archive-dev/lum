package lum.core.model;

import lum.core.impl.model.ClassModelFactory;
import lum.core.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

public interface ClassModel extends Attributable, Member, Generic {
    //    String name(); // includes package
    default String className() {
        return Arrays.asList(name().split("\\.")).getLast();
    }
    default String pkg() {
        return String.join(".", Arrays.asList(name().split("\\.")).removeLast());
    }
    Optional<ClassModel> superClass();

    ClassModel[] interfaces();
    Member[] members();

    Member[] allMembers();
    Optional<MethodModel> getMethod(String name, TypeModel... parameterTypes);

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

    boolean isInterface();

    /// Creates a [ClassModel] from the given [Class], handling array types by extracting the component type.
    ///
    /// @param clazz the {@link Class} to create a model for, may be null
    /// @return {@link Optional} containing the {@link ClassModel}, or empty if clazz is null
    static @NotNull Optional<ClassModel> of(Class<?> clazz) {
        return ClassModelFactory.of(clazz);
    }

    /// Creates a [ClassModel] containing static members (excluding other classes) of the .lum file.
    /// The resulting [ClassModel]'s name ends in Lum
    /// @param path path to .lum file
    /// @return {@link Optional} containing the {@link ClassModel} with static members of the .lum file or empty
    /// if the file doesn't contain any member or there was an error
    /// while parsing the file.
    static @NotNull Optional<ClassModel> fileClass(Path path) {
        return ClassModelFactory.ofFile(path);
    }

    static @NotNull Optional<ClassModel> fileClass(Path workDir, Path path) {
        return ClassModelFactory.ofFile(workDir, path);
    }

    /// Creates an array of [ClassModel]s of classes
    /// declared in the .lum file including the resulting [ClassModel] of [#fileClass].
    /// @param path path to .lum file
    /// @return {@link Optional} containing the array of {@link ClassModel}s or empty
    /// if the file doesn't contain any explicitly declared class
    /// or there was an error while parsing the file.
    static @NotNull Optional<ClassModel[]> ofFile(Path path) {
        return ClassModelFactory.classesFromFile(path);
    }

    static @NotNull Optional<ClassModel[]> ofFile(Path workDir, Path path) {
        return ClassModelFactory.classesFromFile(workDir, path);
    }
}
