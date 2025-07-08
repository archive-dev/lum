package lum.core.impl.model;

/**
 * Base interface for model processors that handle post-creation processing of model objects.
 */
public interface ModelProcessor<T> {
    
    /**
     * Processes the given model object, performing any necessary initialization or validation.
     */
    void process(T model);
}