package lum.compiler.bytecode;

import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.classfile.CodeElement;
import java.lang.classfile.Label;
import java.lang.classfile.Opcode;
import java.lang.constant.ConstantDesc;
import java.util.function.Consumer;

/// @param <K> key type for variable allocation
public interface CodeMaker<K> {
    CodeMaker<K> with(CodeElement codeElement);

    Variable invoke(Variable owner, MethodModel method);
    Variable invoke(Variable owner, MethodModel method, ConstantDesc... arguments);
    Variable invoke(Variable owner, MethodModel method, Variable... arguments);

    Variable getVariable(K key);
    Variable createVariable(K key, TypeModel initialType);
    Variable createVariable(K key, ConstantDesc value);
    Variable createVariable(K key);

    /// Creates instance of InlinedVariable
    InlinedVariable createVariable(TypeModel initialType);
    /// Creates instance of InlinedVariable
    InlinedVariable createVariable(ConstantDesc value);
    /// Creates instance of InlinedVariable
    InlinedVariable createVariable();

    Label createLabel();
    Label createBoundLabel();
    void bindLabel(Label label);

    void ifThen(Variable left, Variable right, Opcode opcode, Consumer<CodeMaker<K>> thenHandler);
    void ifThenElse(Variable left, Variable right, Opcode opcode, Consumer<CodeMaker<K>> thenHandler, Consumer<CodeMaker<K>> elseHandler);
    default void ifeq(Variable left, Variable right, Consumer<CodeMaker<K>> thenHandler) {
        ifThen(left, right, Opcode.IFEQ, thenHandler);
    }
    default void ifeq(Variable left, Variable right, Consumer<CodeMaker<K>> thenHandler, Consumer<CodeMaker<K>> elseHandler) {
        ifThenElse(left, right, Opcode.IFEQ, thenHandler, elseHandler);
    }

    default void ifne(Variable left, Variable right, Consumer<CodeMaker<K>> thenHandler) {
        ifThen(left, right, Opcode.IFNE, thenHandler);
    }
    default void ifne(Variable left, Variable right, Consumer<CodeMaker<K>> thenHandler, Consumer<CodeMaker<K>> elseHandler) {
        ifThenElse(left, right, Opcode.IFNE, thenHandler, elseHandler);
    }

    TypeModel load(ConstantDesc constant);
    TypeModel load(Variable variable);
    void store(Variable variable);

    Variable arrayAccess(Variable array, ConstantDesc... arguments);
    Variable arrayAccess(Variable array, Variable... arguments);

    Variable increment(Variable variable);
    Variable increment(Variable variable, ConstantDesc value);
    Variable increment(Variable variable, Variable value);
    Variable decrement(Variable variable);
    Variable decrement(Variable variable, ConstantDesc value);
    Variable decrement(Variable variable, Variable value);

    Variable not(Variable variable);
    Variable and(Variable left, Variable right);
    Variable xor(Variable left, Variable right);
    Variable or(Variable left, Variable right);

    Variable gt(Variable left, Variable right);
    Variable lt(Variable left, Variable right);
    Variable ge(Variable left, Variable right);
    Variable le(Variable left, Variable right);
    Variable eq(Variable left, Variable right);
    Variable neq(Variable left, Variable right);
    Variable isInstance(Variable left, Variable right);
    Variable in(Variable left, Variable right);

    Variable shr(Variable left, Variable right);
    Variable shl(Variable left, Variable right);
    Variable add(Variable left, Variable right);
    Variable subtract(Variable left, Variable right);
    Variable multiply(Variable left, Variable right);
    Variable divide(Variable left, Variable right);
    Variable remainder(Variable left, Variable right);
    
    Variable cast(Variable from, TypeModel to);

    TypeModel arrayLoad(Variable array);
    void arrayStore(Variable array);
    TypeModel arrayLength(TypeModel arrayType);
    TypeModel newArray(TypeModel arrayType);

    void returnVoid();
    TypeModel returnValue(TypeModel type);
    void throwException();
}
