package lum.core.impl.model;

import lum.core.ir.CodeElement;
import lum.core.model.AttributeModel;

record CodeAttributeImpl(
        CodeElement.CodeBlock codeBlock
) implements AttributeModel.CodeAttribute {}
