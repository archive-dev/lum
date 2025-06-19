package lum.core.model;

import java.lang.classfile.Instruction;
import java.util.List;

public interface AttributeModel {
    interface CodeAttribute extends AttributeModel {
        List<Instruction> instructions();

        void addInstruction(Instruction instruction);
    }
}
