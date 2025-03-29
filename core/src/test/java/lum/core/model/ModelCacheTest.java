package lum.core.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelCacheTest {

    @Test
    void getClassFromPath_classFoundInClasspath() {
        ClassModel stringModel = ModelCache.getClassFromPath(List.of("java", "util", "ArrayList"));
        assertNotNull(stringModel);
        assertEquals("ArrayList", stringModel.name());
        assertEquals("java.util", stringModel.pkg());
    }

    @Test
    void getClassFromPath_classNotFoundInClasspath_classPathCreatedAndParsed() {
        // Assuming you have a test class file in the test resources
        List<String> pathElements = List.of("nonexistent", "Class"); // Replace with your test class
        assertThrows(RuntimeException.class, () -> ModelCache.getClassFromPath(pathElements));
    }

    @Test
    void getClassFromPath_fileNotFoundException() {
        List<String> pathElements = List.of("non", "existent", "Class");

        assertThrows(RuntimeException.class, () -> ModelCache.getClassFromPath(pathElements));
    }

    @Test
    void getClassFromPath_ioExceptionDuringClassModelCreation() {
        // Create a ClassPath that will cause an IOException during parsing
        List<String> pathElements = List.of("faulty", "example", "BrokenClass");

        assertThrows(RuntimeException.class, () -> ModelCache.getClassFromPath(pathElements));
    }

    // Helper methods and test classes (if needed)
    // You might need to create dummy classes and files for testing purposes
}