package lum.core.impl.model;

import java.util.Optional;

/**
 * Base interface for model parsers that convert various input sources into model objects.
 */
public interface ModelParser<T, R> {
    
    /**
     * Parses the input and returns a model object.
     */
    Optional<R> parse(T input);
}