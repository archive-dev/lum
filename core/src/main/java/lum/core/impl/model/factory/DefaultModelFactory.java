package lum.core.impl.model.factory;

import lum.core.impl.model.ModelCache;
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
 * Default implementation of ModelFactory that coordinates between specialized factories.
 */
public class DefaultModelFactory implements ModelFactory {
    
    private final ClassModelFactory classModelFactory;
    private final MethodModelFactory methodModelFactory;
    private final FieldModelFactory fieldModelFactory;
    private final ModelCache modelCache;
    
    public DefaultModelFactory(ModelCache modelCache) {
        this.modelCache = modelCache;
        this.classModelFactory = new ClassModelFactory(modelCache);
        this.methodModelFactory = new MethodModelFactory();
        this.fieldModelFactory = new FieldModelFactory();
    }
    
    @Override
    public Optional<ClassModel> createClassModel(Class<?> clazz) {
        return classModelFactory.createFromClass(clazz);
    }
    
    @Override
    public Optional<ClassModel> createClassModelFromFile(Path workDir, Path path) {
        return classModelFactory.createFromFile(workDir, path);
    }
    
    @Override
    public Optional<TypeModel> createTypeModel(Class<?> clazz) {
        return createClassModel(clazz).map(ClassModel::asTypeModel);
    }
    
    @Override
    public MethodModel createMethodModel(Method method) {
        return methodModelFactory.createFromMethod(method);
    }
    
    @Override
    public MethodModel createMethodModel(Constructor<?> constructor) {
        return methodModelFactory.createFromConstructor(constructor);
    }
    
    @Override
    public FieldModel createFieldModel(Field field) {
        return fieldModelFactory.createFromField(field);
    }
    
    @Override
    public Optional<ClassModel[]> createClassModelsFromFile(Path workDir, Path path) {
        return classModelFactory.createMultipleFromFile(workDir, path);
    }
}