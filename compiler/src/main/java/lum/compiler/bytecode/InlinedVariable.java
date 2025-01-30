package lum.compiler.bytecode;

import java.lang.classfile.Instruction;
import java.util.LinkedList;
import java.util.List;
import java.util.SequencedCollection;
import java.util.function.Consumer;

/// InlinedVariable is a sequence of bytecode instructions that are used
/// to load an actual value onto stack.
public abstract class InlinedVariable implements Variable {
    protected final LinkedList<Consumer<CodeMaker<?>>> codeQueue = new LinkedList<>();
    protected final void addInstruction(Consumer<CodeMaker<?>> instructionHandler) {
        codeQueue.add(instructionHandler);
    }

    protected final void addInstruction(Instruction instruction) {
        codeQueue.add((maker) -> maker.with(instruction));
    }

    protected final void addInstructions(final SequencedCollection<Instruction> instructions) {
        codeQueue.add(maker -> {
            for (var inst : instructions)
                maker.with(inst);
        });
    }

    protected final void addInstructions(final Instruction... instructions) {
        codeQueue.add(maker -> {
            for (var inst : instructions)
                maker.with(inst);
        });
    }

    @SuppressWarnings("unchecked")
    public final List<Consumer<CodeMaker<?>>> getInstructions() {
        return (List<Consumer<CodeMaker<?>>>) codeQueue.clone();
    }
}
