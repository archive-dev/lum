package lum.core.model;

import lum.core.model.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassModelTest {
    // Creating ClassModel instance with valid parameters and verifying all fields are correctly set
    @Test
    public void test_create_class_model_with_valid_parameters() {
        String name = "TestClass";
        HashSet<FieldModel> fields = new HashSet<>();
        HashSet<MethodModel> methods = new HashSet<>();
        List<AttributeModel> annotations = new ArrayList<>();
        HashSet<ClassModel> interfaces = new HashSet<>();
        List<GenericParameter> typeParams = new ArrayList<>();
        AccessFlag[] accessFlags = {AccessFlag.PUBLIC};

        ClassModel model = new ClassModel(name, fields, methods, annotations,
                null, interfaces, typeParams, accessFlags, false, 0);

        assertEquals(name, model.name());
        assertEquals(fields, model.fields());
        assertEquals(methods, model.methods());
        assertEquals(annotations, model.attributes());
        assertNull(model.superClass());
        assertEquals(interfaces, model.interfaces());
        assertEquals(typeParams, model.typeParameters());
        assertEquals(accessFlags, model.accessFlags());
        assertFalse(model.isInterface());
    }

    @Test
    public void test_void_class_model() {
        ClassModel model = ClassModel.from(void.class);
    }

    // Converting a regular Java class to ClassModel using from() method
    @Test
    public void test_convert_regular_class_to_class_model() {
        class TestClass {
            private String field;
            public void method() {}
        }

        ClassModel model = ClassModel.from(TestClass.class);

        assertEquals(TestClass.class.getName(), model.name());
        assertEquals(1, model.fields().size());
        assertEquals(1, model.methods().size());
        assertTrue(model.attributes().isEmpty());
        assertEquals(ClassModelPool.get(Object.class), model.superClass());
        assertTrue(model.interfaces().isEmpty());
        assertFalse(model.isInterface());
    }

    // Converting a class with inheritance hierarchy to ClassModel
    @Test
    public void test_convert_class_with_inheritance() {
        class Parent {}
        class Child extends Parent {}

        ClassModel model = ClassModel.from(Child.class);

        assertEquals(Child.class.getName(), model.name());
        assertNotNull(model.superClass());
        assertEquals(Parent.class.getName(), model.superClass().name());
    }

    // Converting a class implementing interfaces to ClassModel
    @Test
    public void test_convert_class_with_interfaces() {
        interface TestInterface {}
        class TestClass implements TestInterface {}

        ClassModel model = ClassModel.from(TestClass.class);

        assertEquals(1, model.interfaces().size());
        assertEquals(TestInterface.class.getName(),
                model.interfaces().iterator().next().name());
    }

    // Converting null class reference
    @Test
    public void test_convert_null_class() {
        assertNull(ClassModel.from((Class<?>) null));
    }

    class CircularA {
        CircularB b;
    }

    class CircularB {
        CircularA a;
    }

    // Converting class with circular inheritance
    @Test
    public void test_convert_circular_inheritance() {
        ClassModel firstModel = ClassModel.from(CircularA.class);
        ClassModel secondModel = ClassModel.from(CircularB.class);

        assertSame(firstModel, ClassModel.from(CircularA.class));
        assertSame(secondModel, ClassModel.from(CircularB.class));
    }

    // Converting class with no superclass (Object.class)
    @Test
    public void test_convert_object_class() {
        ClassModel model = ClassModel.from(Object.class);

        assertEquals(Object.class.getName(), model.name());
        assertNull(model.superClass());
        assertTrue(model.interfaces().isEmpty());
    }

    // Converting interface instead from class
    @Test
    public void test_convert_interface() {
        interface TestInterface {}

        ClassModel model = ClassModel.from(TestInterface.class);

        assertTrue(model.isInterface());
        assertEquals(TestInterface.class.getName(), model.name());
        assertTrue(model.methods().isEmpty());
    }
}