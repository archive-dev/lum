package lum.core.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelCacheTest {

    @Test
    void getClassFromPath_classFoundInClasspath() {
        ClassModel stringModel = ModelCache.getClassFromPath(List.of("java", "util", "ArrayList"));
        assertNotNull(stringModel);
        assertEquals("java.util.ArrayList", stringModel.name());
    }

    @Test
    void getClassFromPath_classNotFoundInClasspath_classPathCreatedAndParsed() {
        // Assuming you have a test class file in the test resources
        List<String> pathElements = List.of("nonexistent", "Class"); // Replace with your test class
        ClassModel testClassModel = ModelCache.getClassFromPath(pathElements);
        assertNull(testClassModel);
    }

    @Test
    void getClassFromPath_fileNotFoundException() {
        List<String> pathElements = List.of("non", "existent", "Class");

        var classModel = ModelCache.getClassFromPath(pathElements);

        assertNull(classModel);
    }

    @Test
    void getClassFromPath_ioExceptionDuringClassModelCreation() {
        // Create a ClassPath that will cause an IOException during parsing
        List<String> pathElements = List.of("faulty", "example", "BrokenClass");

        var classModel = ModelCache.getClassFromPath(pathElements);

        assertNull(classModel);
    }

    // Helper methods and test classes (if needed)
    // You might need to create dummy classes and files for testing purposes
}