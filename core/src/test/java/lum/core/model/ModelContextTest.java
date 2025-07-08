package lum.core.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModelContextTest {

    private ModelProvider originalProvider;

    @BeforeEach
    void setUp() {
        originalProvider = ModelContext.getProvider();
    }

    @AfterEach
    void tearDown() {
        // Reset to original provider after each test
        ModelContext.setProvider(originalProvider);
    }

    @Test
    void testDefaultProvider() {
        ModelProvider provider = ModelContext.getProvider();
        assertNotNull(provider);
        assertInstanceOf(DefaultModelProvider.class, provider);
    }

    @Test
    void testSetCustomProvider() {
        ModelProvider mockProvider = Mockito.mock(ModelProvider.class);
        
        ModelContext.setProvider(mockProvider);
        
        assertSame(mockProvider, ModelContext.getProvider());
    }

    @Test
    void testSetNullProviderThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ModelContext.setProvider(null);
        });
    }

    @Test
    void testResetToDefault() {
        ModelProvider mockProvider = Mockito.mock(ModelProvider.class);
        ModelContext.setProvider(mockProvider);
        
        ModelContext.resetToDefault();
        
        assertNotSame(mockProvider, ModelContext.getProvider());
        assertInstanceOf(DefaultModelProvider.class, ModelContext.getProvider());
    }

    @Test
    void testWithProviderTemporary() {
        ModelProvider originalProvider = ModelContext.getProvider();
        ModelProvider mockProvider = Mockito.mock(ModelProvider.class);
        
        ModelContext.withProvider(mockProvider, () -> {
            assertSame(mockProvider, ModelContext.getProvider());
        });
        
        // Should be restored after execution
        assertSame(originalProvider, ModelContext.getProvider());
    }

    @Test
    void testWithProviderRestoresOnException() {
        ModelProvider originalProvider = ModelContext.getProvider();
        ModelProvider mockProvider = Mockito.mock(ModelProvider.class);
        
        assertThrows(RuntimeException.class, () -> {
            ModelContext.withProvider(mockProvider, () -> {
                throw new RuntimeException("Test exception");
            });
        });
        
        // Should be restored even after exception
        assertSame(originalProvider, ModelContext.getProvider());
    }

    @Test
    void testClassModelUsesContext() {
        ModelProvider mockProvider = Mockito.mock(ModelProvider.class);
        when(mockProvider.createClassModel(String.class)).thenReturn(Optional.empty());
        
        ModelContext.setProvider(mockProvider);
        
        ClassModel.of(String.class);
        
        verify(mockProvider).createClassModel(String.class);
    }

    @Test
    void testTypeModelUsesContext() {
        ModelProvider mockProvider = Mockito.mock(ModelProvider.class);
        when(mockProvider.createTypeModel(String.class)).thenReturn(Optional.empty());
        
        ModelContext.setProvider(mockProvider);
        
        TypeModel.of(String.class);
        
        verify(mockProvider).createTypeModel(String.class);
    }

    @Test
    void testFileClassUsesContext() {
        ModelProvider mockProvider = Mockito.mock(ModelProvider.class);
        Path testPath = Path.of("test.lum");
        when(mockProvider.createClassModelFromFile(testPath)).thenReturn(Optional.empty());
        
        ModelContext.setProvider(mockProvider);
        
        ClassModel.fileClass(testPath);
        
        verify(mockProvider).createClassModelFromFile(testPath);
    }

    @Test
    void testOfFileUsesContext() {
        ModelProvider mockProvider = Mockito.mock(ModelProvider.class);
        Path testPath = Path.of("test.lum");
        when(mockProvider.createClassModelsFromFile(testPath)).thenReturn(Optional.empty());
        
        ModelContext.setProvider(mockProvider);
        
        ClassModel.ofFile(testPath);
        
        verify(mockProvider).createClassModelsFromFile(testPath);
    }
}