package lum.compiler.jvm;

import lum.compiler.bytecode.Variable;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.classfile.CodeBuilder;
import java.lang.constant.ConstantDesc;

public class JVMVariable implements Variable {
    private final JVMCodeMaker method;
    private final CodeBuilder cb;
    private final int slot;
    /// Made non-final because lum is a dynamic language
    private TypeModel type = TypeModel.of(Object.class);

    public JVMVariable(JVMCodeMaker method, CodeBuilder cb, int slot, TypeModel type) {
        this.method = method;
        this.cb = cb;
        this.slot = slot;
        this.type = type;
    }

    public JVMVariable(JVMCodeMaker method, CodeBuilder cb, int slot) {
        this.method = method;
        this.cb = cb;
        this.slot = slot;
    }

    @Override
    public int slot() {
        return slot;
    }

    @Override
    public TypeModel type() {
        return type;
    }

    @Override
    public Variable set(Variable value) {
        value.load();
        cb.storeLocal(type.typeKind(), slot);
        return this;
    }

    @Override
    public Variable set(ConstantDesc value) {
        method.load(value);
        cb.storeLocal(type.typeKind(), slot);
        return this;
    }

    @Override
    public Variable dynamicSet(ConstantDesc value) {
        type = TypeModel.of(value.getClass());
        return set(value);
    }

    @Override
    public Variable dynamicSet(Variable value) {
        type = value.type();
        return set(value);
    }

    @Override
    public Variable invoke(MethodModel method) {
        return set(this.method.invoke(this, method));
    }

    @Override
    public Variable invoke(MethodModel method, ConstantDesc... arguments) {
        return set(this.method.invoke(this, method, arguments));
    }

    @Override
    public Variable invoke(MethodModel method, Variable... arguments) {
        return set(this.method.invoke(this, method, arguments));
    }

    @Override
    public TypeModel load() {
        return this.method.load(this);
    }

    @Override
    public void store() {
        this.method.store(this);
    }

    @Override
    public Variable arrayAccess(ConstantDesc... arguments) {
        return method.arrayAccess(this, arguments);
    }

    @Override
    public Variable arrayAccess(Variable... arguments) {
        return method.arrayAccess(this, arguments);
    }

    @Override
    public Variable decrement() {
        return method.decrement(this);
    }

    @Override
    public Variable increment() {
        return method.increment(this);
    }

    @Override
    public Variable increment(Variable value) {
        return method.increment(this, value);
    }

    @Override
    public Variable increment(ConstantDesc value) {
        return method.increment(this, value);
    }

    @Override
    public Variable decrement(Variable value) {
        return method.decrement(this, value);
    }

    @Override
    public Variable decrement(ConstantDesc value) {
        return method.decrement(this, value);
    }

    @Override
    public Variable not() {
        return method.not(this);
    }

    @Override
    public Variable and(Variable other) {
        return method.and(this, other);
    }

    @Override
    public Variable xor(Variable other) {
        return method.xor(this, other);
    }

    @Override
    public Variable or(Variable other) {
        return method.or(this, other);
    }

    @Override
    public Variable gt(Variable other) {
        return method.gt(this, other);
    }

    @Override
    public Variable lt(Variable other) {
        return method.lt(this, other);
    }

    @Override
    public Variable ge(Variable other) {
        return method.ge(this, other);
    }

    @Override
    public Variable le(Variable other) {
        return method.le(this, other);
    }

    @Override
    public Variable eq(Variable other) {
        return method.eq(this, other);
    }

    @Override
    public Variable neq(Variable other) {
        return method.neq(this, other);
    }

    @Override
    public Variable isInstance(Variable other) {
        return method.isInstance(this, other);
    }

    @Override
    public Variable in(Variable other) {
        return method.in(this, other);
    }

    @Override
    public Variable shr(Variable other) {
        return method.shr(this, other);
    }

    @Override
    public Variable shl(Variable other) {
        return method.shl(this, other);
    }

    @Override
    public Variable add(Variable other) {
        return method.add(this, other);
    }

    @Override
    public Variable subtract(Variable other) {
        return method.subtract(this, other);
    }

    @Override
    public Variable multiply(Variable other) {
        return method.multiply(this, other);
    }

    @Override
    public Variable divide(Variable other) {
        return method.divide(this, other);
    }

    @Override
    public Variable remainder(Variable other) {
        return method.remainder(this, other);
    }

    @Override
    public Variable cast(TypeModel to) {
        return method.cast(this, to);
    }
}
