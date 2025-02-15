package lum.compiler.codegen.jvm;

import lum.compiler.codegen.CodeMaker;
import lum.compiler.codegen.InlinedVariable;
import lum.compiler.codegen.Variable;
import lum.core.model.TypeModel;

import java.lang.constant.ConstantDesc;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class JVMInlinedVariable extends JVMVariable implements InlinedVariable { // might extend JVMCodeMaker => use BlockCodeBuilder
    private final ArrayList<Consumer<CodeMaker>> code = new ArrayList<>();

    public JVMInlinedVariable() {
        super(-1, null);
    }

    public JVMInlinedVariable(ConstantDesc value) {
        super(-1, null);
        this.type = TypeModel.of(value.getClass());
        code.add(cm -> cm.load(value));
    }

    public JVMInlinedVariable(List<Consumer<CodeMaker>> code) {
        this();
        this.code.addAll(code);
    }

    @Override
    public void load(CodeMaker to) {
        code.forEach(c -> c.accept(to));
    }

    @Override
    public TypeModel getType() {
        throw new IllegalStateException();
    }

    @Override
    public CodeMaker codeMaker() {
        throw new IllegalStateException();
    }

    @Override
    public int getSlot() {
        throw new IllegalStateException();
    }

    @Override
    public void store() {
        throw new IllegalStateException();
    }

    @Override
    public Variable set(ConstantDesc value) {
        throw new IllegalStateException();
    }

    @Override
    public Variable set(Variable value) {
        throw new IllegalStateException();
    }

    @Override
    public Variable set(Consumer<CodeMaker> expression) {
        throw new IllegalStateException();
    }
}
