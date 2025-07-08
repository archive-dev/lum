package lum.core.impl.model;

import lum.core.impl.model.factory.DefaultModelFactory;
import lum.core.impl.model.factory.ModelFactory;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Facade class that provides a simplified API for model creation and management.
 * This class serves as the main entry point for the refactored model package.
 */
public class ModelFacade {
    
    private static final ModelCache DEFAULT_CACHE = new DefaultModelCache();
    private static final ModelFactory DEFAULT_FACTORY = new DefaultModelFactory(DEFAULT_CACHE);
    
    private final ModelFactory factory;
    private final ModelCache cache;
    
    /**
     * Creates a new ModelFacade with default cache and factory.
     */
    public ModelFacade() {
        this(DEFAULT_FACTORY, DEFAULT_CACHE);
    }
    
    /**
     * Creates a new ModelFacade with custom factory and cache.
     */
    public ModelFacade(ModelFactory factory, ModelCache cache) {
        this.factory = factory;
        this.cache = cache;
    }
    
    /**
     * Creates a ClassModel from a Java Class.
     */
    public Optional<ClassModel> createClassModel(Class<?> clazz) {
        return factory.createClassModel(clazz);
    }
    
    /**
     * Creates a ClassModel from a file path.
     */
    public Optional<ClassModel> createClassModelFromFile(Path path) {
        return factory.createClassModelFromFile(null, path);
    }
    
    /**
     * Creates a ClassModel from a file path with custom working directory.
     */
    public Optional<ClassModel> createClassModelFromFile(Path workDir, Path path) {
        return factory.createClassModelFromFile(workDir, path);
    }
    
    /**
     * Creates a TypeModel from a Java Class.
     */
    public Optional<TypeModel> createTypeModel(Class<?> clazz) {
        return factory.createTypeModel(clazz);
    }
    
    /**
     * Creates a MethodModel from a Java Method.
     */
    public MethodModel createMethodModel(Method method) {
        return factory.createMethodModel(method);
    }
    
    /**
     * Creates a MethodModel from a Java Constructor.
     */
    public MethodModel createMethodModel(Constructor<?> constructor) {
        return factory.createMethodModel(constructor);
    }
    
    /**
     * Creates a FieldModel from a Java Field.
     */
    public FieldModel createFieldModel(Field field) {
        return factory.createFieldModel(field);
    }
    
    /**
     * Creates multiple ClassModels from a file containing multiple class declarations.
     */
    public Optional<ClassModel[]> createClassModelsFromFile(Path path) {
        return factory.createClassModelsFromFile(null, path);
    }
    
    /**
     * Creates multiple ClassModels from a file with custom working directory.
     */
    public Optional<ClassModel[]> createClassModelsFromFile(Path workDir, Path path) {
        return factory.createClassModelsFromFile(workDir, path);
    }
    
    /**
     * Clears all cached models.
     */
    public void clearCache() {
        cache.clear();
    }
    
    /**
     * Gets the underlying cache for advanced operations.
     */
    public ModelCache getCache() {
        return cache;
    }
    
    /**
     * Gets the underlying factory for advanced operations.
     */
    public ModelFactory getFactory() {
        return factory;
    }
}