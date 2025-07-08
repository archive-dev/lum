package lum.core.model;

import java.nio.file.Path;
import java.util.Optional;

/**
 * Interface for providing model creation services to the model interfaces.
 * This allows for dependency injection and better testability while maintaining
 * backward compatibility with static factory methods.
 */
public interface ModelProvider {
    
    /**
     * Creates a ClassModel from a Java Class.
     */
    Optional<ClassModel> createClassModel(Class<?> clazz);
    
    /**
     * Creates a TypeModel from a Java Class.
     */
    Optional<TypeModel> createTypeModel(Class<?> clazz);
    
    /**
     * Creates a ClassModel from a file path.
     */
    Optional<ClassModel> createClassModelFromFile(Path path);
    
    /**
     * Creates a ClassModel from a file path with working directory.
     */
    Optional<ClassModel> createClassModelFromFile(Path workDir, Path path);
    
    /**
     * Creates multiple ClassModels from a file containing multiple class declarations.
     */
    Optional<ClassModel[]> createClassModelsFromFile(Path path);
    
    /**
     * Creates multiple ClassModels from a file with working directory.
     */
    Optional<ClassModel[]> createClassModelsFromFile(Path workDir, Path path);
}