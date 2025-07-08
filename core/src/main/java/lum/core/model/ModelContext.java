package lum.core.model;

/**
 * Context class that manages the global ModelProvider instance.
 * This allows for dependency injection while maintaining backward compatibility.
 */
public final class ModelContext {
    
    private static volatile ModelProvider defaultProvider = new DefaultModelProvider();
    
    private ModelContext() {
        // Utility class
    }
    
    /**
     * Gets the current default ModelProvider.
     */
    public static ModelProvider getProvider() {
        return defaultProvider;
    }
    
    /**
     * Sets a custom ModelProvider. This is useful for testing or when using
     * custom implementations.
     * 
     * @param provider the new ModelProvider to use
     */
    public static void setProvider(ModelProvider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("ModelProvider cannot be null");
        }
        defaultProvider = provider;
    }
    
    /**
     * Resets the ModelProvider to the default implementation.
     */
    public static void resetToDefault() {
        defaultProvider = new DefaultModelProvider();
    }
    
    /**
     * Executes a block of code with a temporary ModelProvider.
     * The original provider is restored after execution.
     * 
     * @param provider the temporary provider to use
     * @param runnable the code to execute
     */
    public static void withProvider(ModelProvider provider, Runnable runnable) {
        ModelProvider original = defaultProvider;
        try {
            setProvider(provider);
            runnable.run();
        } finally {
            defaultProvider = original;
        }
    }
}