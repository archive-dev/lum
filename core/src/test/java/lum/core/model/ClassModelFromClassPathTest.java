package lum.core.model;

import org.junit.jupiter.api.Test;

import javax.tools.*;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassModelFromClassPathTest {
    /**
     * A file object used to represent source coming from a string.
     */
    public static class JavaSourceFromString extends SimpleJavaFileObject {
        /**
         * The source code of this "file".
         */
        final String code;

        /**
         * Constructs a new JavaSourceFromString.
         *  @param name the name of the compilation unit represented by this file object
         *  @param code the source code for the compilation unit represented by this file object
         */
        JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),
                    Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    private static final String helloWorldClass = """
        package clazz.org.example;
        
        public class HelloWorld {
            public static void main(String[] args) {
                System.out.println("Hello World!");
            }
        }
        """;

    @Test
    public void test_hello_world_class() throws FileNotFoundException {
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

        JavaFileObject file = new JavaSourceFromString("clazz.org.example.HelloWorld", helloWorldClass);

        var task = javac.getTask(null, null, new DiagnosticCollector<>(), null, null, List.of(file));

        boolean success = task.call();
        if (!success) throw new IllegalArgumentException();

        var classModel = ClassModel.from(ClassPath.from(List.of("clazz", "org", "example", "HelloWorld")));

        assertEquals(0, classModel.fields().size());
        assertEquals(1, classModel.methods().size());
        assertEquals(ClassModelPool.get(Object.class), classModel.superClass());
        assertTrue(classModel.interfaces().isEmpty());
        assertFalse(classModel.isInterface());
        assertEquals("clazz.org.example.HelloWorld", classModel.name());
    }
}
