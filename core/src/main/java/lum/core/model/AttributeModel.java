package lum.core.model;

import lum.core.ir.CodeElement;

public interface AttributeModel {
    interface CodeAttribute extends AttributeModel {
        CodeElement.CodeBlock codeBlock();
    }
}
