package lum.core.model;

import lum.core.ir.CodeElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CodeAttributeTest {
    private static Path RESOURCES_PATH;
    @BeforeAll
    static void setup() {
        RESOURCES_PATH = Path.of("src/test/resources");
    }

    @Test
    void test_correct_code_attribute() {
        Path file = RESOURCES_PATH.resolve("WithCode.lum");

        ClassModel[] classes = ClassModel.ofFile(file).orElseThrow();
        MethodModel method = classes[0].getMethod("main", TypeModel.STRING.asArray(1)).orElseThrow();
        AttributeModel attribute = method.attributes()[0];
        assertInstanceOf(AttributeModel.CodeAttribute.class, attribute);

        AttributeModel.CodeAttribute code = ((AttributeModel.CodeAttribute) attribute);
        assertInstanceOf(CodeElement.CallExpression.class, code.codeBlock().elements().getFirst());
        assertInstanceOf(CodeElement.ForEachLoopElement.class, code.codeBlock().elements().get(1));
    }
}
