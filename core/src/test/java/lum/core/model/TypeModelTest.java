package lum.core.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TypeModelTest {
    // Returns OBJECT when types have different array dimensions
    @Test
    public void test_returns_object_when_types_have_different_array_dimensions() {
        // Arrange
        TypeModel stringArray = TypeModel.STRING.asArray(1);
        TypeModel intArray = TypeModel.INT.asArray(2);

        // Act
        TypeModel result = TypeModel.unionOf(stringArray, intArray);

        // Assert
        assertEquals(TypeModel.OBJECT.asArray(1), result);
    }

    // Returns OBJECT.asArray(dimensions) when types contain OBJECT
    @Test
    public void test_returns_object_array_when_types_contain_object() {
        // Arrange
        TypeModel stringArray = TypeModel.STRING.asArray(2);
        TypeModel objectArray = TypeModel.OBJECT.asArray(2);

        // Act
        TypeModel result = TypeModel.unionOf(stringArray, objectArray);

        // Assert
        assertEquals(TypeModel.OBJECT.asArray(2), result);
    }

    // Returns common superclass with correct array dimensions when all types share a superclass
    @Test
    public void test_returns_common_superclass_with_correct_dimensions() {
        // Arrange
        ClassModel numberModel = ClassModel.of(Number.class);
        ClassModel integerModel = ClassModel.of(Integer.class);
        ClassModel doubleModel = ClassModel.of(Double.class);

        TypeModel integerType = integerModel.typeModel(1);
        TypeModel doubleType = doubleModel.typeModel(1);

        // Act
        TypeModel result = TypeModel.unionOf(integerType, doubleType);

        // Assert
        assertEquals(numberModel.typeModel(1), result);
    }

    // Returns common interface with correct array dimensions when all types share an interface
    @Test
    public void test_returns_common_interface_with_correct_dimensions() {
        // Arrange
        ClassModel stringModel = ClassModel.of(String.class);
        ClassModel integerModel = ClassModel.of(Integer.class);

        TypeModel stringType = stringModel.typeModel(1);
        TypeModel integerType = integerModel.typeModel(1);

        // Act
        TypeModel result = TypeModel.unionOf(stringType, integerType);

        // Assert
        assertEquals(TypeModel.OBJECT.asArray(1), result);
    }

    // Empty array of types
    @Test
    public void test_empty_array_of_types() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, TypeModel::unionOf);
    }

    // Single type in the array
    @Test
    public void test_single_type_in_array() {
        // Arrange
        TypeModel stringType = TypeModel.STRING.asArray(1);

        // Act
        TypeModel result = TypeModel.unionOf(stringType);

        // Assert
        assertEquals(stringType, result);
    }

    // Types with no common superclass or interfaces
    @Test
    public void test_types_with_no_common_superclass_or_interfaces() {
        TypeModel stringType = TypeModel.STRING;
        TypeModel intType = TypeModel.INT;

        // Act
        TypeModel result = TypeModel.unionOf(stringType, intType);

        // Assert
        assertEquals(TypeModel.OBJECT, result);
    }

    // Types with multiple common interfaces
    @Test
    public void test_types_with_multiple_common_interfaces() {
        // Arrange
        ClassModel stringModel = ClassModel.of(String.class);
        ClassModel integerModel = ClassModel.of(Integer.class);

        TypeModel stringType = stringModel.typeModel(2);
        TypeModel integerType = integerModel.typeModel(2);

        // Act
        TypeModel result = TypeModel.unionOf(stringType, integerType);

        // Assert
        assertEquals(TypeModel.OBJECT.asArray(2), result);
    }
}
