package lum.core.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassPathTest {
    @Test
    public void test_complex_class() throws FileNotFoundException {
        Path expectedPath = Path.of("clazz/org/example");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("clazz", "org", "example", "Example.class");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "clazz", "org", "example", "Example"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("Example", classPath.className());
        assertEquals(Extension.CLASS, classPath.extension());
        assertEquals("Example.class", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }

    @Test
    public void test_inner_class() throws FileNotFoundException {
        Path expectedPath = Path.of("clazz/org/example");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("clazz", "org", "example", "Example$Inner.class");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "clazz", "org", "example", "Example", "Inner"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("Inner", classPath.className());
        assertEquals(Extension.CLASS, classPath.extension());
        assertEquals("Example$Inner.class", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }

    @Test
    public void test_nested_class() throws FileNotFoundException {
        Path expectedPath = Path.of("clazz/org/example");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("clazz", "org", "example", "Example$Middle$Inner.class");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "clazz", "org", "example", "Example", "Middle", "Inner"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("Inner", classPath.className());
        assertEquals(Extension.CLASS, classPath.extension());
        assertEquals("Example$Middle$Inner.class", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }


    @Test
    public void test_simple_class() throws FileNotFoundException {
        Path expectedPath = Path.of("");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("ExampleClazz.class");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "ExampleClazz"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("ExampleClazz", classPath.className());
        assertEquals(Extension.CLASS, classPath.extension());
        assertEquals("ExampleClazz.class", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }

    @Test
    public void test_complex_lum() throws FileNotFoundException {
        Path expectedPath = Path.of("lum/org/example");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("lum", "org", "example", "Example.lum");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "lum", "org", "example", "Example"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("Example", classPath.className());
        assertEquals(Extension.LUM, classPath.extension());
        assertEquals("Example.lum", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }

    @Test
    public void test_inner_lum() throws FileNotFoundException {
        Path expectedPath = Path.of("lum/org/example");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("lum", "org", "example", "Example.lum");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "lum", "org", "example", "Example", "Inner"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("Inner", classPath.className());
        assertEquals(Extension.LUM, classPath.extension());
        assertEquals("Example.lum", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }

    @Test
    public void test_nested_lum() throws FileNotFoundException {
        Path expectedPath = Path.of("lum/org/example");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("lum", "org", "example", "Example.lum");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "lum", "org", "example", "Example", "Middle", "Inner"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("Inner", classPath.className());
        assertEquals(Extension.LUM, classPath.extension());
        assertEquals("Example.lum", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }


    @Test
    public void test_simple_lum() throws FileNotFoundException {
        Path expectedPath = Path.of("");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("ExampleLum.lum");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "ExampleLum"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("ExampleLum", classPath.className());
        assertEquals(Extension.LUM, classPath.extension());
        assertEquals("ExampleLum.lum", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }

    @Test
    public void test_complex_java() throws FileNotFoundException {
        Path expectedPath = Path.of("java/org/example");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("java", "org", "example", "Example.java");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "java", "org", "example", "Example"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("Example", classPath.className());
        assertEquals(Extension.JAVA, classPath.extension());
        assertEquals("Example.java", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }

    @Test
    public void test_inner_java() throws FileNotFoundException {
        Path expectedPath = Path.of("java/org/example");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("java", "org", "example", "Example.java");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "java", "org", "example", "Example", "Inner"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("Inner", classPath.className());
        assertEquals(Extension.JAVA, classPath.extension());
        assertEquals("Example.java", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }

    @Test
    public void test_nested_java() throws FileNotFoundException {
        Path expectedPath = Path.of("java/org/example");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("java", "org", "example", "Example.java");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "java", "org", "example", "Example", "Middle", "Inner"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("Inner", classPath.className());
        assertEquals(Extension.JAVA, classPath.extension());
        assertEquals("Example.java", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }


    @Test
    public void test_simple_java() throws FileNotFoundException {
        Path expectedPath = Path.of("");
        try {
            Files.createDirectories(expectedPath);
            Path path = Path.of("ExampleJava.java");
            if (!path.toFile().exists())
                Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pathElements = List.of(
                "ExampleJava"
        );

        var classPath = ClassPath.from(pathElements);

        assertEquals("ExampleJava", classPath.className());
        assertEquals(Extension.JAVA, classPath.extension());
        assertEquals("ExampleJava.java", classPath.fileName());
        assertEquals(expectedPath, classPath.path());
    }
    
    @Test
    public void test_nonexistent_class() {
//        Path expectedPath = Path.of("non", "existent", "Class");

        List<String> pathElements = List.of(
                "non", "existent", "Class"
        );

        assertThrows(FileNotFoundException.class, () -> ClassPath.from(pathElements));
    }
}