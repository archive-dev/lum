package lum.core.impl.model.factory;

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
 * Central factory interface for creating model objects.
 * Provides a unified API for model creation while delegating to specialized factories.
 */
public interface ModelFactory {
    
    /**
     * Creates a ClassModel from a Java Class.
     */
    Optional<ClassModel> createClassModel(Class<?> clazz);
    
    /**
     * Creates a ClassModel from a file path.
     */
    Optional<ClassModel> createClassModelFromFile(Path workDir, Path path);
    
    /**
     * Creates a TypeModel from a Java Class.
     */
    Optional<TypeModel> createTypeModel(Class<?> clazz);
    
    /**
     * Creates a MethodModel from a Java Method.
     */
    MethodModel createMethodModel(Method method);
    
    /**
     * Creates a MethodModel from a Java Constructor.
     */
    MethodModel createMethodModel(Constructor<?> constructor);
    
    /**
     * Creates a FieldModel from a Java Field.
     */
    FieldModel createFieldModel(Field field);
    
    /**
     * Creates multiple ClassModels from a file containing multiple class declarations.
     */
    Optional<ClassModel[]> createClassModelsFromFile(Path workDir, Path path);
}