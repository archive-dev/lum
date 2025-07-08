package lum.core.model;

import lum.core.impl.model.IntersectionClassModelImpl;
import lum.core.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

public interface ClassModel extends Attributable, Member, Generic {
    ClassModel INT = ClassModel.of(int.class).orElseThrow();
    ClassModel LONG = ClassModel.of(long.class).orElseThrow();
    ClassModel FLOAT = ClassModel.of(float.class).orElseThrow();
    ClassModel DOUBLE = ClassModel.of(double.class).orElseThrow();
    ClassModel BYTE = ClassModel.of(byte.class).orElseThrow();
    ClassModel SHORT = ClassModel.of(short.class).orElseThrow();
    ClassModel CHAR = ClassModel.of(char.class).orElseThrow();
    ClassModel STRING = ClassModel.of(String.class).orElseThrow();
    ClassModel OBJECT = ClassModel.of(Object.class).orElseThrow();
    ClassModel BOOLEAN = ClassModel.of(boolean.class).orElseThrow();
    ClassModel VOID = ClassModel.of(void.class).orElseThrow();

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

    interface IntersectionClassModel extends ClassModel {
        ClassModel[] getClassModels();
        ClassModel getPrimaryClassModel();
        boolean isSatisfiedBy(ClassModel classModel);

        static IntersectionClassModel of(ClassModel[] models) {
            return new IntersectionClassModelImpl(models);
        }

        static IntersectionClassModel of(Class<?>[] classes) {
            return new IntersectionClassModelImpl(Arrays.stream(classes).map(ClassModel::of).map(Optional::orElseThrow).toArray(ClassModel[]::new));
        }
    }

    /// Creates a [ClassModel] from the given [Class], handling array types by extracting the component type.
    ///
    /// @param clazz the {@link Class} to create a model for, may be null
    /// @return {@link Optional} containing the {@link ClassModel}, or empty if clazz is null
    static @NotNull Optional<ClassModel> of(Class<?> clazz) {
        return ModelContext.getProvider().createClassModel(clazz);
    }

    /// Creates a [ClassModel] containing static members (excluding other classes) of the .lum file.
    /// The resulting [ClassModel]'s name ends in Lum
    /// @param path path to .lum file
    /// @return {@link Optional} containing the {@link ClassModel} with static members of the .lum file or empty
    /// if the file doesn't contain any member or there was an error
    /// while parsing the file.
    static @NotNull Optional<ClassModel> fileClass(Path path) {
        return ModelContext.getProvider().createClassModelFromFile(path);
    }

    static @NotNull Optional<ClassModel> fileClass(Path workDir, Path path) {
        return ModelContext.getProvider().createClassModelFromFile(workDir, path);
    }

    /// Creates an array of [ClassModel]s of classes
    /// declared in the .lum file including the resulting [ClassModel] of [#fileClass].
    /// @param path path to .lum file
    /// @return {@link Optional} containing the array of {@link ClassModel}s or empty
    /// if the file doesn't contain any explicitly declared class
    /// or there was an error while parsing the file.
    static @NotNull Optional<ClassModel[]> ofFile(Path path) {
        return ModelContext.getProvider().createClassModelsFromFile(path);
    }

    static @NotNull Optional<ClassModel[]> ofFile(Path workDir, Path path) {
        return ModelContext.getProvider().createClassModelsFromFile(workDir, path);
    }
}
