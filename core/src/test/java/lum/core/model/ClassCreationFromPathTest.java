package lum.core.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ClassCreationFromPathTest {
    @Test
    public void test_creates_class_model_from_valid_class_declaration() throws IOException {
        ClassPath path = new ClassPath(Path.of("src/test/resources"), "Test.lum", "TestClass");

        ClassModel result = ParserModelFactory.createClassModel(path);

        assertNotNull(result);
        assertFalse(result.isInterface());
        assertEquals("TestClass", result.name());
    }

    // Creates ClassModel from valid ClassPath with interface declaration
    @Test
    public void test_creates_class_model_from_valid_interface_declaration() throws IOException {
        ClassPath path = new ClassPath(Path.of("src/test/resources"), "TestInterface.lum", "TestInterface");

        ClassModel result = ParserModelFactory.createClassModel(path);

        assertNotNull(result);
        assertTrue(result.isInterface());
        assertEquals("TestInterface", result.name());
    }

    // Successfully processes import statements and creates an Imports model
    @Test
    public void test_processes_import_statements() throws IOException {
        ClassPath path = new ClassPath(Path.of("src/test/resources"), "WithImports.lum", "TestClass");

        ClassModel result = ParserModelFactory.createClassModel(path);

        assertNotNull(result);
        assertTrue(result.interfaces().length > 0);
        assertNotNull(result.superClass());
    }

    // Correctly maps class declarations to their respective models
    @Test
    public void test_maps_declarations_to_models() throws IOException {
        ClassPath path = new ClassPath(Path.of("src/test/resources"), "MultipleClasses.lum", "InnerClass");

        ClassModel result = ParserModelFactory.createClassModel(path);

        assertNotNull(result);
        assertEquals("InnerClass", result.name());
    }

    // ClassPath points to a non-existent file or invalid path
    @Test
    public void test_invalid_classpath_throws_exception() {
        ClassPath path = new ClassPath(Path.of("nonexistent"), "Missing.lum", "TestClass");

        assertThrows(NoSuchFileException.class, () -> ParserModelFactory.createClassModel(path));
    }

    // The Program contains no matching class name
    @Test
    public void test_no_matching_class_returns_null() throws IOException {
        ClassPath path = new ClassPath(Path.of("src/test/resources"), "Valid.lum", "NonExistentClass");

        ClassModel result = ParserModelFactory.createClassModel(path);

        assertNull(result);
    }

    // The Program contains multiple declarations with the same class name
    @Test
    public void test_multiple_declarations_same_name() throws IOException {
        ClassPath path = new ClassPath(Path.of("src/test/resources"), "DuplicateClass.lum", "DuplicateClass");

        ClassModel result = ParserModelFactory.createClassModel(path);

        assertNotNull(result);
        assertEquals("DuplicateClass", result.name());
    }

    // Import statements contain invalid or non-existent classes
    @Test
    public void test_invalid_imports_handling() {
        ClassPath path = new ClassPath(Path.of("src/test/resources"), "InvalidImports.lum", "TestClass");

        assertThrows(RuntimeException.class, () -> ParserModelFactory.createClassModel(path));
    }
}
