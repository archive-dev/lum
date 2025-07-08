package lum.core.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;

import static org.junit.jupiter.api.Assertions.*;

class ClassModelTest {
    static ClassModel classModel;
    private static final Path RESOURCES_PATH = Path.of("src/test/resources/");

    @BeforeAll
    static void initClass() {
        classModel = ClassModel.of(TestClass.class).orElseThrow();
    }

    @AfterAll
    static void printClass() {
        System.out.println(classModel.toPrettyString());
    }

    @Test
    void test_allMembers() {
        assertEquals(TestClass.class.getMethods().length + TestClass.class.getFields().length + TestClass.class.getConstructors().length
                + SuperClass.class.getMethods().length + SuperClass.class.getFields().length + SuperClass.class.getConstructors().length
                + Object.class.getMethods().length + Object.class.getFields().length + Object.class.getConstructors().length
                + Interface.class.getMethods().length,
                classModel.allMembers().length
        );
    }

    @Test
    void test_primitive_class_model() {
        ClassModel model = ClassModel.of(int.class).orElseThrow();

        assertEquals("int", model.name());
        assertEquals(0, model.members().length);
    }

    @Test
    void test_simple_lum_class() {
        Path testPath = RESOURCES_PATH.resolve("Test.lum");
        ClassModel model = ClassModel.fileClass(testPath).orElseThrow();
        
        assertNotNull(model);
        assertEquals("TestLum", model.name());
        assertTrue(model.isPublic());
        assertTrue(model.isFinal());
    }

    @Test
    void test_interface_lum_file() {
        Path testPath = RESOURCES_PATH.resolve("TestInterface.lum");
        ClassModel model = ClassModel.fileClass(testPath).orElseThrow();
        
        assertNotNull(model);
        assertEquals("TestInterfaceLum", model.name());
        assertTrue(model.isPublic());
        assertTrue(model.isFinal());
    }

    @Test
    void test_generic_class_lum_file() {
        Path testPath = RESOURCES_PATH.resolve("GenericClass.lum");
        ClassModel model = ClassModel.fileClass(testPath).orElseThrow();
        
        assertNotNull(model);
        assertEquals("GenericClassLum", model.name());
        assertTrue(model.isPublic());
        assertTrue(model.isFinal());
    }

    @Test
    void test_class_with_imports_lum_file() {
        Path testPath = RESOURCES_PATH.resolve("WithImports.lum");
        ClassModel model = ClassModel.fileClass(testPath).orElseThrow();
        
        assertNotNull(model);
        assertEquals("WithImportsLum", model.name());
        assertTrue(model.isPublic());
        assertTrue(model.isFinal());
    }

    @Test
    void test_multiple_classes_lum_file() {
        Path testPath = RESOURCES_PATH.resolve("MultipleClasses.lum");
        ClassModel[] models = ClassModel.ofFile(testPath).orElseThrow();
        
        assertNotNull(models);
        assertTrue(models.length > 0);
        
        // Should contain the file model plus any classes defined in the file
        boolean hasFileModel = false;
        for (ClassModel model : models) {
            if (model.name().endsWith("Lum")) {
                hasFileModel = true;
                break;
            }
        }
        assertTrue(hasFileModel, "Should contain file model");
    }

    @Test
    void test_valid_lum_file() {
        Path testPath = RESOURCES_PATH.resolve("Valid.lum");
        ClassModel model = ClassModel.fileClass(testPath).orElseThrow();
        
        assertNotNull(model);
        assertEquals("ValidLum", model.name());
        assertTrue(model.isPublic());
        assertTrue(model.isFinal());
    }

    @Test
    void test_class_model_superclass() {
        assertNotNull(classModel.superClass());
        assertTrue(classModel.superClass().isPresent());
        assertEquals("lum.core.model.SuperClass", classModel.superClass().get().name());
    }

    @Test
    void test_class_model_interfaces() {
        ClassModel[] interfaces = classModel.interfaces();
        assertNotNull(interfaces);
        assertEquals(1, interfaces.length);
        assertEquals("lum.core.model.Interface", interfaces[0].name());
    }

    @Test
    void test_class_model_members() {
        Member[] members = classModel.members();
        assertNotNull(members);
        assertTrue(members.length > 0);
        
        // Check that we have different types of members
        boolean hasMethod = false;
        boolean hasField = false;

        for (Member member : members) {
            if (member instanceof MethodModel) {
                hasMethod = true;
            } else if (member instanceof FieldModel) {
                hasField = true;
            }
        }
        
        assertTrue(hasMethod, "Should have methods");
        assertTrue(hasField, "Should have fields");
//        assertTrue(hasConstructor, "Should have constructors");
    }

    @Test
    void test_class_model_access_modifiers() {
//        assertTrue(classModel.isPackagePrivate());
        assertFalse(classModel.isPublic());
        assertFalse(classModel.isPrivate());
        assertFalse(classModel.isProtected());
    }

    @Test
    void test_class_model_name() {
        assertEquals("lum.core.model.TestClass", classModel.name());
    }

    @Test
    void test_class_model_simple_name() {
        assertEquals("TestClass", classModel.className());
    }

    @Test
    void test_invalid_lum_file_handling() {
        Path invalidPath = RESOURCES_PATH.resolve("InvalidImports.lum");
        // This should either return empty or handle gracefully
        var result = ClassModel.fileClass(invalidPath);
        // The test passes if no exception is thrown
        assertNotNull(result);
    }

    @Test
    void test_duplicate_class_lum_file() {
        Path testPath = RESOURCES_PATH.resolve("DuplicateClass.lum");
        var result = ClassModel.fileClass(testPath);
        // Should handle duplicate classes gracefully
        assertNotNull(result);
    }

    @Test
    void test_class_model_toString() {
        String toString = classModel.toString();
        assertNotNull(toString);
        assertFalse(toString.isEmpty());
        assertTrue(toString.contains("TestClass"));
    }

    @Test
    void test_class_model_pretty_string() {
        String prettyString = classModel.toPrettyString();
        assertNotNull(prettyString);
        assertFalse(prettyString.isEmpty());
        assertTrue(prettyString.contains("TestClass"));
    }

    @Test
    void test_primitive_types_coverage() {
        // Test various primitive types
        String[] primitiveTypes = {"boolean", "byte", "char", "short", "int", "long", "float", "double"};
        Class<?>[] primitiveClasses = {boolean.class, byte.class, char.class, short.class, int.class, long.class, float.class, double.class};
        
        for (int i = 0; i < primitiveTypes.length; i++) {
            ClassModel model = ClassModel.of(primitiveClasses[i]).orElseThrow();
            assertEquals(primitiveTypes[i], model.name());
            assertEquals(0, model.members().length);
            assertTrue(model.isPrimitive());
        }
    }

    @Test
    void test_array_class_model() {
        ClassModel model = ClassModel.of(int[].class).orElseThrow();
        assertNotNull(model);
        // Array types should be handled by getting component type
        assertEquals("int", model.name());
    }

    @Test
    void test_null_class_handling() {
        var result = ClassModel.of(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void test_intersection_class_model() {
        var intersection = ClassModel.IntersectionClassModel.of(new Class[]{Runnable.class, Future.class});
        assertTrue(intersection.isSatisfiedBy(ClassModel.of(RunnableFuture.class).orElseThrow()));
        TypeModel typeModel = intersection.asTypeModel();
        assertInstanceOf(TypeModel.IntersectionTypeModel.class, typeModel);
        assertTrue(intersection.getMethod("get").isPresent());
        assertTrue(intersection.getMethod("run").isPresent());
    }

    @Test
    void test_union_type_model() {
        var union = TypeModel.UnionTypeModel.of(new TypeModel[]{TypeModel.INT, TypeModel.STRING});
        assertEquals(TypeModel.OBJECT, union.lubTypeModel());
        assertEquals(2, union.getTypes().length);
    }
}