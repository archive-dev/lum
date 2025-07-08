package lum.core.impl;

import lum.core.impl.model.ModelFacade;
import lum.core.impl.model.PrimitiveClassModelImpl;
import lum.core.model.ClassModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClassModelFactoryTest {

    @TempDir
    Path tempDir;

    private ModelFacade facade;

    @BeforeEach
    void setUp() {
        // Create a fresh facade for each test to ensure clean state
        facade = new ModelFacade();
    }

    @Test
    void testCreateClassModelFromValidClassWithCaching() {
        // First call
        Optional<ClassModel> result1 = ClassModel.of(String.class);
        assertTrue(result1.isPresent());
        assertEquals("java.lang.String", result1.get().name());

        // Second call should return cached result
        Optional<ClassModel> result2 = ClassModel.of(String.class);
        assertTrue(result2.isPresent());
        assertSame(result1.get(), result2.get());
    }

    @Test
    void testCreateClassModelFromValidFilePath() throws IOException {
        // Create a temporary file with valid content
        Path testFile = tempDir.resolve("TestClass.lum");
        Files.writeString(testFile, "class TestClass { }\n");

        var result = ClassModel.ofFile(testFile);
        assertTrue(result.isPresent());
        assertTrue(result.get()[0].name().contains("TestClass"));
        assertEquals(Object.class.getName(), result.get()[0].superClass().get().name());
    }

    @Test
    void testProcessClassInterfacesAndMembers() {
        Optional<ClassModel> result = ClassModel.of(StringBuilder.class);
        assertTrue(result.isPresent());
        
        ClassModel model = result.get();
        assertEquals("java.lang.StringBuilder", model.name());
        
        // Verify interfaces are processed
        assertTrue(model.interfaces().length > 0);
        
        // Verify members are processed
        assertTrue(model.members().length > 0);
    }

    @Test
    void testHandleNullClassParameter() {
        Optional<ClassModel> result = ClassModel.of(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreatePrimitiveClassModel() {
        Optional<ClassModel> result = ClassModel.of(int.class);
        assertTrue(result.isPresent());
        
        ClassModel model = result.get();
        assertEquals("int", model.name());
        assertTrue(model instanceof PrimitiveClassModelImpl);
        assertTrue(model.superClass().isEmpty());
        assertEquals(0, model.interfaces().length);
        assertEquals(0, model.members().length);
    }

    @Test
    void testHandleFileParsingFailure() throws IOException {
        // Create a file that would cause parsing to fail or return empty
        Path invalidFile = tempDir.resolve("invalid.lum");
        Files.writeString(invalidFile, "invalid content that cannot be parsed");

        // This test assumes FileParser.parseFile returns Optional.empty() for invalid files
        // The actual behavior depends on FileParser implementation
        assertDoesNotThrow(() -> {
            ClassModel.fileClass(invalidFile);
            // The result may be present or empty depending on FileParser behavior
            // We're mainly testing that no exception is thrown
        });
    }
}