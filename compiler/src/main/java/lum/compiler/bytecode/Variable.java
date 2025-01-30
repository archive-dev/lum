package lum.compiler.bytecode;

import lum.core.model.MethodModel;
import lum.core.model.TypeModel;
import org.jetbrains.annotations.Contract;

import java.lang.constant.ConstantDesc;

@SuppressWarnings("UnstableApiUsage")
public interface Variable {
    int slot();
    TypeModel type();
    @Contract(mutates = "this") Variable set(Variable value);
    @Contract(mutates = "this") Variable set(ConstantDesc value);

    @Contract(mutates = "this") Variable dynamicSet(Variable value);
    @Contract(mutates = "this") Variable dynamicSet(ConstantDesc value);

    Variable invoke(MethodModel method);
    Variable invoke(MethodModel method, ConstantDesc... arguments);
    Variable invoke(MethodModel method, Variable... arguments);

    TypeModel load();
    void store();

    Variable arrayAccess(ConstantDesc... arguments);
    Variable arrayAccess(Variable... arguments);

    Variable increment();
    Variable increment(Variable value);
    Variable increment(ConstantDesc value);
    Variable decrement();
    Variable decrement(Variable value);
    Variable decrement(ConstantDesc value);

    Variable not();
    Variable and(Variable other);
    Variable xor(Variable other);
    Variable or(Variable other);

    Variable gt(Variable other);
    Variable lt(Variable other);
    Variable ge(Variable other);
    Variable le(Variable other);
    Variable eq(Variable other);
    Variable neq(Variable other);
    Variable isInstance(Variable other);
    Variable in(Variable other);

    Variable shr(Variable other);
    Variable shl(Variable other);
    Variable add(Variable other);
    Variable subtract(Variable other);
    Variable multiply(Variable other);
    Variable divide(Variable other);
    Variable remainder(Variable other);

    @Contract(mutates = "this") Variable cast(TypeModel to);
}
