package lum.compiler.codegen;

import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.classfile.CodeBuilder;
import java.lang.constant.ConstantDesc;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface CodeMaker {
    CodeBuilder codeBuilder();

    Variable this_();
    Variable parameter(int index);

    CodeMaker ifThen(Variable value, Consumer<CodeMaker> ifHandler);
    CodeMaker ifThenElse(Variable value, Consumer<CodeMaker> ifHandler, Consumer<CodeMaker> elseHandler);

    CodeMaker return_();
    CodeMaker return_(Variable value);
    CodeMaker return_(ConstantDesc value);

    CodeMaker break_();
    CodeMaker continue_();

    CodeMaker for_(Variable i, Consumer<CodeMaker> condition, BiConsumer<CodeMaker, Variable> update, Consumer<CodeMaker> block);
    CodeMaker foreach(Variable i, Variable iterator, Consumer<CodeMaker> block);

    CodeMaker while_(Consumer<CodeMaker> condition, Consumer<CodeMaker> block);
    CodeMaker doWhile(Consumer<CodeMaker> condition, Consumer<CodeMaker> block);

    CodeMaker load(Variable variable);
    CodeMaker load(ConstantDesc value);

    Variable var(String name, TypeModel type, ConstantDesc value);
    Variable var(String name, TypeModel type, Variable value);
    default Variable var(String name, TypeModel type) {
        return var(name, type, (Variable) null);
    }

    default Variable var(String name, Variable value) {
        return var(name, null, value);
    }
    default Variable var(String name, ConstantDesc value) {
        return var(name, null, value);
    }

    default Variable var(String name) {
        return var(name, null, (ConstantDesc) null);
    }

    InlinedVariableBuilder var(TypeModel type, Variable value);
    default InlinedVariableBuilder var(TypeModel type) {
        return var(type, (Variable) null);
    }

    default InlinedVariableBuilder var(Variable value) {
        return var(value.getType(), value);
    }

    default InlinedVariableBuilder var() {
        return var((TypeModel) null, (Variable) null);
    }

    InlinedVariable var(TypeModel type, ConstantDesc value);
    default InlinedVariable var(ConstantDesc value) {
        return var(TypeModel.of(value.getClass()), value);
    }

    Variable invokestatic(MethodModel method);
    Variable invokestatic(MethodModel method, Variable... arguments);
    Variable invokestatic(MethodModel method, ConstantDesc... arguments);
}
