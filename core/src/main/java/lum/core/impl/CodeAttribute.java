package lum.core.impl;

import lum.core.model.AttributeModel;

import java.lang.classfile.Instruction;
import java.util.ArrayList;
import java.util.List;

public class CodeAttribute implements AttributeModel.CodeAttribute {
    private final List<Instruction> instructions = new ArrayList<>();

    @Override
    public List<Instruction> instructions() {
        return instructions;
    }

    @Override
    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }
}
