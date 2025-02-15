package lum.compiler.codegen;

import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.constant.ConstantDesc;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface InlinedVariableBuilder {
    void setCode(ArrayList<Consumer<CodeMaker>> code);

    InlinedVariableBuilder addCode(Consumer<CodeMaker> code);

    InlinedVariable build();

    InlinedVariableBuilder ifThen(Variable value, Consumer<CodeMaker> ifHandler);

    InlinedVariableBuilder this_();

    InlinedVariableBuilder parameter(int index);

    InlinedVariableBuilder ifThenElse(Variable value, Consumer<CodeMaker> ifHandler, Consumer<CodeMaker> elseHandler);

    InlinedVariableBuilder var(ConstantDesc value);

    InlinedVariableBuilder return_();

    InlinedVariableBuilder var(TypeModel type, Variable value);

    InlinedVariableBuilder invokestatic(MethodModel method);

    InlinedVariableBuilder invokestatic(MethodModel method, ConstantDesc... arguments);

    InlinedVariableBuilder var();

    InlinedVariableBuilder var(String name);

    InlinedVariableBuilder while_(Consumer<CodeMaker> condition, Consumer<CodeMaker> block);

    InlinedVariableBuilder var(String name, TypeModel type, Variable value);

    InlinedVariableBuilder var(String name, TypeModel type, ConstantDesc value);

    InlinedVariableBuilder return_(Variable value);

    InlinedVariableBuilder var(String name, TypeModel type);

    InlinedVariableBuilder return_(ConstantDesc value);

    InlinedVariableBuilder var(TypeModel type);

    InlinedVariableBuilder break_();

    InlinedVariableBuilder var(String name, Variable value);

    InlinedVariableBuilder var(TypeModel type, ConstantDesc value);

    InlinedVariableBuilder continue_();

    InlinedVariableBuilder doWhile(Consumer<CodeMaker> condition, Consumer<CodeMaker> block);

    InlinedVariableBuilder invokestatic(MethodModel method, Variable... arguments);

    InlinedVariableBuilder load(ConstantDesc value);

    InlinedVariableBuilder var(Variable value);

    InlinedVariableBuilder for_(Variable i, Consumer<CodeMaker> condition, BiConsumer<CodeMaker, Variable> update, Consumer<CodeMaker> block);

    InlinedVariableBuilder foreach(Variable i, Variable iterator, Consumer<CodeMaker> block);

    InlinedVariableBuilder load(Variable variable);
}
