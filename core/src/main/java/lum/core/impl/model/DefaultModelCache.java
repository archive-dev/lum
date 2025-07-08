package lum.core.impl.model;

import lum.core.model.ClassModel;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe implementation of ModelCache using concurrent hash maps.
 */
public class DefaultModelCache implements ModelCache {
    
    private final Map<Class<?>, @NotNull ClassModel> classCache = new ConcurrentHashMap<>();
    private final Map<Path, @NotNull ClassModel> pathCache = new ConcurrentHashMap<>();
    private final Set<@NotNull ClassModel> processedModels = ConcurrentHashMap.newKeySet();
    
    @Override
    public Optional<ClassModel> getClassModel(Class<?> clazz) {
        return Optional.ofNullable(classCache.get(clazz));
    }
    
    @Override
    public void putClassModel(Class<?> clazz, @NotNull ClassModel classModel) {
        classCache.put(clazz, classModel);
    }
    
    @Override
    public Optional<ClassModel> getClassModel(Path path) {
        return Optional.ofNullable(pathCache.get(path));
    }
    
    @Override
    public void putClassModel(Path path, @NotNull ClassModel classModel) {
        pathCache.put(path, classModel);
    }
    
    @Override
    public boolean isProcessed(@NotNull ClassModel classModel) {
        return processedModels.contains(classModel);
    }
    
    @Override
    public void markAsProcessed(@NotNull ClassModel classModel) {
        processedModels.add(classModel);
    }
    
    @Override
    public Set<@NotNull ClassModel> getProcessedModels() {
        return Collections.unmodifiableSet(processedModels);
    }
    
    @Override
    public void clear() {
        classCache.clear();
        pathCache.clear();
        processedModels.clear();
    }
}