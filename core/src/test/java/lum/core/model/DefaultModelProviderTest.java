package lum.core.model;

import lum.core.impl.model.ModelFacade;
import lum.core.impl.model.PrimitiveClassModelImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DefaultModelProviderTest {

    @TempDir
    Path tempDir;

    private DefaultModelProvider provider;

    @BeforeEach
    void setUp() {
        provider = new DefaultModelProvider();
    }

    @Test
    void testCreateClassModelFromClass() {
        Optional<ClassModel> result = provider.createClassModel(String.class);

        assertTrue(result.isPresent());
        assertEquals("java.lang.String", result.get().name());
        assertNotNull(result.get().superClass());
        assertTrue(result.get().members().length > 0);
    }

    @Test
    void testCreateClassModelFromNullClass() {
        Optional<ClassModel> result = provider.createClassModel(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateClassModelFromPrimitiveClass() {
        Optional<ClassModel> result = provider.createClassModel(int.class);

        assertTrue(result.isPresent());
        assertEquals("int", result.get().name());
        assertTrue(result.get() instanceof PrimitiveClassModelImpl);
    }

    @Test
    void testCreateTypeModelFromClass() {
        Optional<TypeModel> result = provider.createTypeModel(String.class);

        assertTrue(result.isPresent());
        assertEquals("java.lang.String", result.get().model().name());
        assertEquals(0, result.get().arrayDimensions());
    }

    @Test
    void testCreateTypeModelFromNullClass() {
        Optional<TypeModel> result = provider.createTypeModel(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateClassModelFromFile() throws IOException {
        Path testFile = tempDir.resolve("TestClass.lum");
        Files.writeString(testFile, "class TestClass { }");

        Optional<ClassModel> result = provider.createClassModelFromFile(testFile);

        assertTrue(result.isPresent());
        assertTrue(result.get().name().contains("TestClass"));
    }

    @Test
    void testCreateClassModelFromFileWithWorkDir() throws IOException {
        Path testFile = tempDir.resolve("TestClass.lum");
        Files.writeString(testFile, "class TestClass { }");

        Optional<ClassModel> result = provider.createClassModelFromFile(tempDir, testFile);

        assertTrue(result.isPresent());
        assertTrue(result.get().name().contains("TestClass"));
    }

    @Test
    void testCreateClassModelsFromFile() throws IOException {
        Path testFile = tempDir.resolve("MultipleClasses.lum");
        Files.writeString(testFile, "class FirstClass { } class SecondClass { }");

        Optional<ClassModel[]> result = provider.createClassModelsFromFile(testFile);

        assertTrue(result.isPresent());
        assertTrue(result.get().length > 0);
    }

    @Test
    void testCreateClassModelsFromFileWithWorkDir() throws IOException {
        Path testFile = tempDir.resolve("MultipleClasses.lum");
        Files.writeString(testFile, "class FirstClass { }\nclass SecondClass { }");

        Optional<ClassModel[]> result = provider.createClassModelsFromFile(tempDir, testFile);

        assertTrue(result.isPresent());
        assertTrue(result.get().length > 0);
    }

    @Test
    void testConstructorWithCustomFacade() {
        ModelFacade customFacade = new ModelFacade();
        DefaultModelProvider customProvider = new DefaultModelProvider(customFacade);

        // Test that it works with custom facade
        Optional<ClassModel> result = customProvider.createClassModel(String.class);
        assertTrue(result.isPresent());
        assertEquals("java.lang.String", result.get().name());
    }

    @Test
    void testCaching() {
        // First call
        Optional<ClassModel> result1 = provider.createClassModel(String.class);
        assertTrue(result1.isPresent());

        // Second call should return cached result
        Optional<ClassModel> result2 = provider.createClassModel(String.class);
        assertTrue(result2.isPresent());
        assertSame(result1.get(), result2.get());
    }
}