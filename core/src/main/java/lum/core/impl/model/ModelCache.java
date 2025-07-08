package lum.core.impl.model;

import lum.core.model.ClassModel;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

/**
 * Interface for caching model objects to improve performance and avoid duplicate creation.
 */
public interface ModelCache {
    
    /**
     * Gets a cached ClassModel for the given Class.
     */
    Optional<ClassModel> getClassModel(Class<?> clazz);
    
    /**
     * Caches a ClassModel for the given Class.
     */
    void putClassModel(Class<?> clazz, @NotNull ClassModel classModel);
    
    /**
     * Gets a cached ClassModel for the given file path.
     */
    Optional<ClassModel> getClassModel(Path path);
    
    /**
     * Caches a ClassModel for the given file path.
     */
    void putClassModel(Path path, @NotNull ClassModel classModel);
    
    /**
     * Checks if a ClassModel has been processed (to avoid reprocessing).
     */
    boolean isProcessed(@NotNull ClassModel classModel);
    
    /**
     * Marks a ClassModel as processed.
     */
    void markAsProcessed(@NotNull ClassModel classModel);
    
    /**
     * Gets all processed ClassModels.
     */
    Set<@NotNull ClassModel> getProcessedModels();
    
    /**
     * Clears all cached data.
     */
    void clear();
}