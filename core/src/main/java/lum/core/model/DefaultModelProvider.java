package lum.core.model;

import lum.core.impl.model.ModelFacade;

import java.nio.file.Path;
import java.util.Optional;

/**
 * Default implementation of ModelProvider that delegates to ModelFacade.
 * This provides the bridge between the model interfaces and the implementation.
 */
public class DefaultModelProvider implements ModelProvider {
    
    private final ModelFacade facade;
    
    public DefaultModelProvider() {
        this.facade = new ModelFacade();
    }
    
    public DefaultModelProvider(ModelFacade facade) {
        this.facade = facade;
    }
    
    @Override
    public Optional<ClassModel> createClassModel(Class<?> clazz) {
        return facade.createClassModel(clazz);
    }
    
    @Override
    public Optional<TypeModel> createTypeModel(Class<?> clazz) {
        return facade.createTypeModel(clazz);
    }
    
    @Override
    public Optional<ClassModel> createClassModelFromFile(Path path) {
        return facade.createClassModelFromFile(path);
    }
    
    @Override
    public Optional<ClassModel> createClassModelFromFile(Path workDir, Path path) {
        return facade.createClassModelFromFile(workDir, path);
    }
    
    @Override
    public Optional<ClassModel[]> createClassModelsFromFile(Path path) {
        return facade.createClassModelsFromFile(path);
    }
    
    @Override
    public Optional<ClassModel[]> createClassModelsFromFile(Path workDir, Path path) {
        return facade.createClassModelsFromFile(workDir, path);
    }
}