package lum.compiler.jvm;

import lum.compiler.bytecode.InlinedVariable;
import lum.compiler.bytecode.Variable;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.classfile.CodeBuilder;
import java.lang.constant.ConstantDesc;
import java.util.ArrayList;

public class JVMInlinedVariable extends InlinedVariable { // TODO
    private final JVMCodeMaker method;
    private final CodeBuilder cb;
    /// Made non-final because lum is a dynamic language
    private TypeModel type;

    private final ArrayList<JVMInlinedVariable> createdVariables = new ArrayList<>();

    public JVMInlinedVariable(JVMCodeMaker method, CodeBuilder cb, TypeModel type) {
        this.method = method;
        this.cb = cb;
        this.type = type;
    }

    public JVMInlinedVariable(JVMCodeMaker method, CodeBuilder cb) {
        this.method = method;
        this.cb = cb;
    }

    @Override
    public int slot() {
        return -1;
    }

    @Override
    public TypeModel type() {
        return type;
    }

    @Override
    public Variable set(Variable value) {
        return this;
    }

    @Override
    public Variable set(ConstantDesc value) {
        return this;
    }

    @Override
    public Variable dynamicSet(Variable value) {
        return this;
    }

    @Override
    public Variable dynamicSet(ConstantDesc value) {
        return this;
    }

    @Override
    public Variable invoke(MethodModel method) {
        return null;
    }

    @Override
    public Variable invoke(MethodModel method, ConstantDesc... arguments) {
        return null;
    }

    @Override
    public Variable invoke(MethodModel method, Variable... arguments) {
        return null;
    }

    @Override
    public TypeModel load() {
        return null;
    }

    @Override
    public void store() {

    }

    @Override
    public Variable arrayAccess(ConstantDesc... arguments) {
        return null;
    }

    @Override
    public Variable arrayAccess(Variable... arguments) {
        return null;
    }

    @Override
    public Variable increment() {
        return null;
    }

    @Override
    public Variable increment(Variable value) {
        return null;
    }

    @Override
    public Variable increment(ConstantDesc value) {
        return null;
    }

    @Override
    public Variable decrement() {
        return null;
    }

    @Override
    public Variable decrement(Variable value) {
        return null;
    }

    @Override
    public Variable decrement(ConstantDesc value) {
        return null;
    }

    @Override
    public Variable not() {
        return null;
    }

    @Override
    public Variable and(Variable other) {
        return null;
    }

    @Override
    public Variable xor(Variable other) {
        return null;
    }

    @Override
    public Variable or(Variable other) {
        return null;
    }

    @Override
    public Variable gt(Variable other) {
        return null;
    }

    @Override
    public Variable lt(Variable other) {
        return null;
    }

    @Override
    public Variable ge(Variable other) {
        return null;
    }

    @Override
    public Variable le(Variable other) {
        return null;
    }

    @Override
    public Variable eq(Variable other) {
        return null;
    }

    @Override
    public Variable neq(Variable other) {
        return null;
    }

    @Override
    public Variable isInstance(Variable other) {
        return null;
    }

    @Override
    public Variable in(Variable other) {
        return null;
    }

    @Override
    public Variable shr(Variable other) {
        return null;
    }

    @Override
    public Variable shl(Variable other) {
        return null;
    }

    @Override
    public Variable add(Variable other) {
        return null;
    }

    @Override
    public Variable subtract(Variable other) {
        return null;
    }

    @Override
    public Variable multiply(Variable other) {
        return null;
    }

    @Override
    public Variable divide(Variable other) {
        return null;
    }

    @Override
    public Variable remainder(Variable other) {
        return null;
    }

    @Override
    public Variable cast(TypeModel to) {
        return null;
    }
}
