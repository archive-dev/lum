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
    private CodeMaker cm = null;

    public JVMInlinedVariable() {
        super(-1, null);
    }

    public JVMInlinedVariable(ConstantDesc value) {
        super(-1, null);
        this.type = TypeModel.of(value);
        code.add(cm -> cm.load(value));
    }

    public JVMInlinedVariable(List<Consumer<CodeMaker>> code) {
        this();
        this.code.addAll(code);
    }

    public JVMInlinedVariable(TypeModel type, List<Consumer<CodeMaker>> code) {
        this();
        this.type = type;
        this.code.addAll(code);
    }

    public JVMInlinedVariable(TypeModel type, CodeMaker cm, List<Consumer<CodeMaker>> code) {
        this();
        this.type = type;
        this.cm = cm;
        this.code.addAll(code);
    }

    @Override
    public void load(CodeMaker to) {
        setCodeMaker(to);
        code.forEach(c -> c.accept(to));
    }

    @Override
    public TypeModel getType() {
        if (type != null)
            return type;
        return TypeModel.OBJECT;
    }

    @Override
    public CodeMaker codeMaker() {
        if (cm == null) throw new IllegalStateException();
        return cm;
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
    public Variable set(TypeModel newType, ConstantDesc value) {
        throw new IllegalStateException();
    }

    @Override
    public Variable set(TypeModel newType, Variable value) {
        throw new IllegalStateException();
    }

    @Override
    public Variable set(TypeModel newType, Consumer<CodeMaker> expression) {
        throw new IllegalStateException();
    }

    void setCodeMaker(CodeMaker cm) {
        this.cm = cm;
    }
}
