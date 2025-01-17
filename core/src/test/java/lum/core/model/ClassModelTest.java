package lum.core.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.AccessFlag;

import static org.junit.jupiter.api.Assertions.*;

class ClassModelTest {
    @SuppressWarnings("unused")
    static class TestClass {
        private int privateField;
        protected String protectedField;
        public File publicField;
        float packagePrivateField;

        static final int CONSTANT_FIELD = 42;
        volatile boolean volatileField;
        transient long transientField;

        public static void main(String[] args) {}

        private void privateMethod() {}
        protected void protectedMethod() {}
        public void publicMethod() {}
        void packagePrivateMethod() {}

        File getFileMethod() { return null; }

        void methodWithParameters(int a, float b, File file) {}
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
        assertNotNull(classModel.getMethod("publicMethod"));
        assertNotNull(classModel.getMethod("protectedMethod"));
        assertNotNull(classModel.getMethod("packagePrivateMethod"));
        assertNotNull(classModel.getMethod("privateMethod"));
    }

    @Test
    void test_get_method_with_return_type() {
        MethodModel fileMethod = classModel.getMethod("getFileMethod");
        assertNotNull(fileMethod);
        assertEquals(TypeModel.of(File.class), fileMethod.returnType());
    }

    @Test
    void test_get_method_with_parameters() {
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
        assertTrue(methods.length >= 6); // Including inherited methods

        // Verify specific methods exist
        assertTrue(containsMethodNamed(methods, "privateMethod"));
        assertTrue(containsMethodNamed(methods, "protectedMethod"));
        assertTrue(containsMethodNamed(methods, "publicMethod"));
        assertTrue(containsMethodNamed(methods, "packagePrivateMethod"));
        assertTrue(containsMethodNamed(methods, "getFileMethod"));
        assertTrue(containsMethodNamed(methods, "methodWithParameters"));
    }

    @Test
    void test_method_access_flags() {
        assertTrue(classModel.getMethod("privateMethod").accessFlags().contains(AccessFlag.PRIVATE));
        assertTrue(classModel.getMethod("protectedMethod").accessFlags().contains(AccessFlag.PROTECTED));
        assertTrue(classModel.getMethod("publicMethod").accessFlags().contains(AccessFlag.PUBLIC));

        // Package-private method should not have private, protected, or public flags
        MethodModel packagePrivateMethod = classModel.getMethod("packagePrivateMethod");
        assertFalse(packagePrivateMethod.accessFlags().contains(AccessFlag.PRIVATE));
        assertFalse(packagePrivateMethod.accessFlags().contains(AccessFlag.PROTECTED));
        assertFalse(packagePrivateMethod.accessFlags().contains(AccessFlag.PUBLIC));
    }

    // New field tests
    @Test
    void test_get_field_by_name() {
        assertNotNull(classModel.getField("privateField"));
        assertNotNull(classModel.getField("protectedField"));
        assertNotNull(classModel.getField("publicField"));
        assertNotNull(classModel.getField("packagePrivateField"));
    }

    @Test
    void test_field_types() {
        assertEquals(TypeModel.of(int.class), classModel.getField("privateField").type());
        assertEquals(TypeModel.of(String.class), classModel.getField("protectedField").type());
        assertEquals(TypeModel.of(File.class), classModel.getField("publicField").type());
        assertEquals(TypeModel.of(float.class), classModel.getField("packagePrivateField").type());
    }

    @Test
    void test_fields_collection() {
        FieldModel[] fields = classModel.fields();
        assertNotNull(fields);
        assertTrue(fields.length >= 7); // Including all declared fields

        assertTrue(containsFieldNamed(fields, "privateField"));
        assertTrue(containsFieldNamed(fields, "protectedField"));
        assertTrue(containsFieldNamed(fields, "publicField"));
        assertTrue(containsFieldNamed(fields, "packagePrivateField"));
        assertTrue(containsFieldNamed(fields, "CONSTANT_FIELD"));
        assertTrue(containsFieldNamed(fields, "volatileField"));
        assertTrue(containsFieldNamed(fields, "transientField"));
    }

    @Test
    void test_field_access_flags() {
        assertTrue(classModel.getField("privateField").accessFlags().contains(AccessFlag.PRIVATE));
        assertTrue(classModel.getField("protectedField").accessFlags().contains(AccessFlag.PROTECTED));
        assertTrue(classModel.getField("publicField").accessFlags().contains(AccessFlag.PUBLIC));

        // Package-private field should not have private, protected, or public flags
        FieldModel packagePrivateField = classModel.getField("packagePrivateField");
        assertFalse(packagePrivateField.accessFlags().contains(AccessFlag.PRIVATE));
        assertFalse(packagePrivateField.accessFlags().contains(AccessFlag.PROTECTED));
        assertFalse(packagePrivateField.accessFlags().contains(AccessFlag.PUBLIC));
    }

    @Test
    void test_field_modifiers() {
        FieldModel constantField = classModel.getField("CONSTANT_FIELD");
        assertTrue(constantField.accessFlags().contains(AccessFlag.STATIC));
        assertTrue(constantField.accessFlags().contains(AccessFlag.FINAL));

        FieldModel volatileField = classModel.getField("volatileField");
        assertTrue(volatileField.accessFlags().contains(AccessFlag.VOLATILE));

        FieldModel transientField = classModel.getField("transientField");
        assertTrue(transientField.accessFlags().contains(AccessFlag.TRANSIENT));
    }

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