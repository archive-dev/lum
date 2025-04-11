package lum.core.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.AccessFlag;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ClassModelTest {
    static class SuperClass {
        public void superMethod() {}
    }

    @SuppressWarnings("unused")
    static class TestClass extends SuperClass {
        private int privateField;
        protected String protectedField;
        public File publicField;
        float packagePrivateField;

        static final int CONSTANT_FIELD = 42;
        volatile boolean volatileField;
        transient long transientField;

        public static void main(String[] args) {
        }

        private void privateMethod() {
        }

        protected void protectedMethod() {
        }

        public void publicMethod() {
        }

        void packagePrivateMethod() {
        }

        public File getFileMethod() {
            return null;
        }

        public void methodWithParameters(int a, float b, File file) {
        }
    }

    static ClassModel classModel;

    @BeforeAll
    static void initClass() {
        classModel = ClassModel.of(TestClass.class);
    }

    @AfterAll
    static void printClass() {
        System.out.println(classModel);
    }

    @Test
    void test_get_method_without_parameters() {
        // Only public methods should be accessible now
        assertNotNull(classModel.getMethod("publicMethod"));
        assertNull(classModel.getMethod("protectedMethod"));
        assertNull(classModel.getMethod("packagePrivateMethod"));
        assertNull(classModel.getMethod("privateMethod"));
    }

    @Test
    void test_get_superclass_method() {
        assertNotNull(classModel.getMethod("superMethod"));
    }

    @Test
    void test_get_method_with_return_type() {
        // Made getFileMethod public in the test class
        MethodModel fileMethod = classModel.getMethod("getFileMethod");
        assertNotNull(fileMethod);
        assertEquals(TypeModel.of(File.class), fileMethod.returnType());
    }

    @Test
    void test_get_method_with_parameters() {
        // Made methodWithParameters public in the test class
        MethodModel method = classModel.getMethod("methodWithParameters",
                TypeModel.of(int.class),
                TypeModel.of(float.class),
                TypeModel.of(File.class)
        );
        assertNotNull(method);

        ParameterModel[] parameters = method.parameters();
        assertEquals(3, parameters.length);
        assertEquals(TypeModel.of(int.class), parameters[0].type());
        assertEquals(TypeModel.of(float.class), parameters[1].type());
        assertEquals(TypeModel.of(File.class), parameters[2].type());
    }

    @Test
    void test_methods_collection() {
        MethodModel[] methods = classModel.methods();
        assertNotNull(methods);

        // Only public methods should be included
        // Plus inherited public methods from Object and SuperClass
        assertTrue(methods.length >= 15);

        // Verify only public methods exist
        assertTrue(containsMethodNamed(methods, "publicMethod"));
        assertTrue(containsMethodNamed(methods, "getFileMethod"));
        assertTrue(containsMethodNamed(methods, "methodWithParameters"));
        assertTrue(containsMethodNamed(methods, "superMethod"));

        // Non-public methods should not be present
        assertFalse(containsMethodNamed(methods, "privateMethod"));
        assertFalse(containsMethodNamed(methods, "protectedMethod"));
        assertFalse(containsMethodNamed(methods, "packagePrivateMethod"));
    }

    @Test
    void test_method_access_flags() {
        // Only test public methods now
        assertTrue(classModel.getMethod("publicMethod").accessFlags().contains(AccessFlag.PUBLIC));

        // Non-public methods should not be found
        assertNull(classModel.getMethod("privateMethod"));
        assertNull(classModel.getMethod("protectedMethod"));
        assertNull(classModel.getMethod("packagePrivateMethod"));
    }

    // New field tests
    @Test
    void test_get_field_by_name() {
        // Only public fields should be accessible
        assertNull(classModel.getField("privateField"));
        assertNull(classModel.getField("protectedField"));
        assertNotNull(classModel.getField("publicField"));
        assertNull(classModel.getField("packagePrivateField"));
    }

    @Test
    void test_field_types() {
        // Only test public fields
        assertEquals(TypeModel.of(File.class), classModel.getField("publicField").type());

        // Non-public fields should not be found
        assertNull(classModel.getField("privateField"));
        assertNull(classModel.getField("protectedField"));
        assertNull(classModel.getField("packagePrivateField"));
    }

    @Test
    void test_fields_collection() {
        FieldModel[] fields = classModel.fields();
        assertNotNull(fields);

        // Only public fields should be included
        // CONSTANT_FIELD is static final but not explicitly public, so it shouldn't be included
        assertTrue(fields.length >= 1);

        // Only public fields should be present
        assertTrue(containsFieldNamed(fields, "publicField"));

        // Non-public fields should not be present
        assertFalse(containsFieldNamed(fields, "privateField"));
        assertFalse(containsFieldNamed(fields, "protectedField"));
        assertFalse(containsFieldNamed(fields, "packagePrivateField"));
        assertFalse(containsFieldNamed(fields, "CONSTANT_FIELD"));
        assertFalse(containsFieldNamed(fields, "volatileField"));
        assertFalse(containsFieldNamed(fields, "transientField"));
    }

    @Test
    void test_field_access_flags() {
        // Only test public fields
        assertTrue(classModel.getField("publicField").accessFlags().contains(AccessFlag.PUBLIC));

        // Non-public fields should not be found
        assertNull(classModel.getField("privateField"));
        assertNull(classModel.getField("protectedField"));
        assertNull(classModel.getField("packagePrivateField"));
    }

    @Test
    void test_field_modifiers() {
        // Since CONSTANT_FIELD, volatileField, and transientField are not public,
        // they should not be accessible
        assertNull(classModel.getField("CONSTANT_FIELD"));
        assertNull(classModel.getField("volatileField"));
        assertNull(classModel.getField("transientField"));
    }

    @Test
    void test_generic_class() {
        var classes = ModelsParser.parseModels(Path.of("src", "test", "resources", "GenericClass.lum"));

        assertEquals(2, classes.size());
        var genericTestClass = classes.stream().filter(c -> c.name().equals("GenericClass")).findFirst().orElseThrow();
        var otherClass = classes.stream().filter(c -> c.name().equals("Other")).findFirst().orElseThrow();

        assertEquals(2, genericTestClass.genericArguments().length);
        assertEquals("T", genericTestClass.genericArguments()[0].name());
        assertEquals(otherClass.typeModel(), genericTestClass.genericArguments()[0].bounds()[0]);

        System.out.println(classes.stream().map(ClassModel::genericArguments).map(Arrays::toString).toList());
    }

//    @Test
//    void test_are_annotations_present() {
//        assertNotEquals(0, classModel.annotations().length);
//    }

    private boolean containsFieldNamed(FieldModel[] fields, String fieldName) {
        for (FieldModel field : fields) {
            if (field.name().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsMethodNamed(MethodModel[] methods, String methodName) {
        for (MethodModel method : methods) {
            if (method.name().equals(methodName)) {
                return true;
            }
        }
        return false;
    }
}