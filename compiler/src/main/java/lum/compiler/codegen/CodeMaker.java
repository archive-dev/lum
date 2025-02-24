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
    Variable super_();
    Variable parameter(int index);

    CodeMaker ifThen(Variable value, Consumer<CodeMaker> ifHandler);
    CodeMaker ifThenElse(Variable value, Consumer<CodeMaker> ifHandler, Consumer<CodeMaker> elseHandler);

    CodeMaker return_();
    CodeMaker return_(Variable value);
    CodeMaker return_(ConstantDesc value);

    CodeMaker break_();
    CodeMaker continue_();

    CodeMaker for_(Variable i, BiConsumer<CodeMaker, Variable> condition, BiConsumer<CodeMaker, Variable> update, BiConsumer<CodeMaker, Variable> block);

    /// Iterates over the elements of an iterable or array, executing a provided block of code for each element.
    ///
    /// If the iterable is an instance of [Iterable], the method obtains an iterator and uses it to traverse
    /// the elements. For each element, the provided block is executed with a [CodeMaker] and a [Variable]
    /// representing the current element.
    ///
    ///
    /// If the iterable is an array, a traditional `for` loop is used to iterate over the elements. The block is
    /// executed for each element with a [CodeMaker] and a [Variable] representing the current element.
    ///
    ///
    /// @param iterable The [Variable] representing the iterable or array to iterate over.
    ///                 Must be of a type that implements [Iterable] or is an array.
    /// @param block    A [BiConsumer] that accepts a [CodeMaker] and a [Variable] representing
    ///                 the current element in the iteration. This block contains the code to be executed for each
    ///                 element.
    /// @return The [CodeMaker] instance, allowing for method chaining.
    /// @throws IllegalStateException if the provided `iterable` is neither an [Iterable] nor an array.
    CodeMaker foreach(Variable iterable, BiConsumer<CodeMaker, Variable> block);

    CodeMaker foreach(Variable i, Variable iterable, BiConsumer<CodeMaker, Variable> block);

    CodeMaker while_(Consumer<CodeMaker> condition, Consumer<CodeMaker> block);
    CodeMaker doWhile(Consumer<CodeMaker> condition, Consumer<CodeMaker> block);

    CodeMaker load(Variable variable);
    CodeMaker load(ConstantDesc value);

    Variable new_(TypeModel type, Variable... arguments);
    Variable superInit(Variable... arguments);
    Variable newArray(TypeModel ofType, int size);

    boolean hasVariable(String name);

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
        return var(TypeModel.of(value), value);
    }

    Variable invokestatic(MethodModel method);
    Variable invokestatic(String methodName);
    Variable invokestatic(MethodModel method, Variable... arguments);
    Variable invokestatic(String methodName, Variable... arguments);
    Variable invokestatic(MethodModel method, ConstantDesc... arguments);
    Variable invokestatic(String methodName, ConstantDesc... arguments);
}
