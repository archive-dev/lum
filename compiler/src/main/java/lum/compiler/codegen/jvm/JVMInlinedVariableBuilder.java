package lum.compiler.codegen.jvm;

import lum.compiler.codegen.*;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.constant.ConstantDesc;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class JVMInlinedVariableBuilder implements InlinedVariableBuilder {
    private ArrayList<Consumer<CodeMaker>> code = new ArrayList<>();
    private TypeModel type = TypeModel.of(Object.class);
    private CodeMaker cm = null;

	public JVMInlinedVariableBuilder() {}

	public JVMInlinedVariableBuilder(List<Consumer<CodeMaker>> code) {
        this.code.addAll(code);
    }

    @Override
    public void setCode(ArrayList<Consumer<CodeMaker>> code) {
        this.code = code;
    }

    @Override
    public InlinedVariableBuilder setType(TypeModel type) {
        this.type = type;
        return this;
    }

    @Override
    public InlinedVariableBuilder setCodeMaker(CodeMaker codeMaker) {
        this.cm = codeMaker;
        return this;
    }

    @Override
	public JVMInlinedVariableBuilder addCode(Consumer<CodeMaker> code) {
        this.code.add(code);
        return this;
    }

    @Override
    public JVMInlinedVariable build() {
        return new JVMInlinedVariable(this.type, this.cm, code);
    }

    @Override
	public JVMInlinedVariableBuilder ifThen(Variable value, Consumer<CodeMaker> ifHandler) {
        return addCode((c) -> c.ifThen(value, ifHandler));
    }

    @Override
	public JVMInlinedVariableBuilder this_() {
        return addCode(CodeMaker::this_);
    }

    @Override
	public JVMInlinedVariableBuilder parameter(int index) {
        return addCode((c) -> c.parameter(index));
    }

    @Override
	public JVMInlinedVariableBuilder ifThenElse(Variable value, Consumer<CodeMaker> ifHandler, Consumer<CodeMaker> elseHandler) {
        return addCode((c) -> c.ifThenElse(value, ifHandler, elseHandler));
    }

    @Override
	public JVMInlinedVariableBuilder var(ConstantDesc value) {
        return addCode((c) -> c.var(value));
    }

    @Override
	public JVMInlinedVariableBuilder return_() {
        return addCode(CodeMaker::return_);
    }

    @Override
	public JVMInlinedVariableBuilder var(TypeModel type, Variable value) {
        return addCode((c) -> c.var(type, value));
    }

    @Override
	public JVMInlinedVariableBuilder invokestatic(MethodModel method) {
        return addCode((c) -> c.invokestatic(method));
    }

    @Override
	public JVMInlinedVariableBuilder invokestatic(MethodModel method, ConstantDesc... arguments) {
        return addCode((c) -> c.invokestatic(method, arguments));
    }

    @Override
	public JVMInlinedVariableBuilder var() {
        return addCode(CodeMaker::var);
    }

    @Override
	public JVMInlinedVariableBuilder var(String name) {
        return addCode((c) -> c.var(name));
    }

    @Override
	public JVMInlinedVariableBuilder while_(Consumer<CodeMaker> condition, Consumer<CodeMaker> block) {
        return addCode((c) -> c.while_(condition, block));
    }

    @Override
	public JVMInlinedVariableBuilder var(String name, TypeModel type, Variable value) {
        return addCode((c) -> c.var(name, type, value));
    }

    @Override
	public JVMInlinedVariableBuilder var(String name, TypeModel type, ConstantDesc value) {
        return addCode((c) -> c.var(name, type, value));
    }

    @Override
	public JVMInlinedVariableBuilder return_(Variable value) {
        return addCode((c) -> c.return_(value));
    }

    @Override
	public JVMInlinedVariableBuilder var(String name, TypeModel type) {
        return addCode((c) -> c.var(name, type));
    }

    @Override
	public JVMInlinedVariableBuilder return_(ConstantDesc value) {
        return addCode((c) -> c.return_(value));
    }

    @Override
	public JVMInlinedVariableBuilder var(TypeModel type) {
        return addCode((c) -> c.var(type));
    }

    @Override
	public JVMInlinedVariableBuilder break_() {
        return addCode(CodeMaker::break_);
    }

    @Override
	public JVMInlinedVariableBuilder var(String name, Variable value) {
        return addCode((c) -> c.var(name, value));
    }

    @Override
	public JVMInlinedVariableBuilder var(TypeModel type, ConstantDesc value) {
        return addCode((c) -> c.var(type, value));
    }

    @Override
	public JVMInlinedVariableBuilder continue_() {
        return addCode(CodeMaker::continue_);
    }

    @Override
	public JVMInlinedVariableBuilder doWhile(Consumer<CodeMaker> condition, Consumer<CodeMaker> block) {
        return addCode((c) -> c.doWhile(condition, block));
    }

    @Override
	public JVMInlinedVariableBuilder invokestatic(MethodModel method, Variable... arguments) {
        return addCode((c) -> c.invokestatic(method, arguments));
    }

    @Override
	public JVMInlinedVariableBuilder load(ConstantDesc value) {
        return addCode((c) -> c.load(value));
    }

    @Override
	public JVMInlinedVariableBuilder var(Variable value) {
        return addCode((c) -> c.var(value));
    }

    @Override
	public JVMInlinedVariableBuilder for_(Variable i, BiConsumer<CodeMaker, Variable> condition, BiConsumer<CodeMaker, Variable> update, BiConsumer<CodeMaker, Variable> block) {
        return addCode((c) -> c.for_(i, condition, update, block));
    }

    @Override
	public JVMInlinedVariableBuilder foreach(Variable iterable, BiConsumer<CodeMaker, Variable> block) {
        return addCode((c) -> c.foreach(iterable, block));
    }

    @Override
	public JVMInlinedVariableBuilder load(Variable variable) {
        return addCode((c) -> c.load(variable));
    }
}
