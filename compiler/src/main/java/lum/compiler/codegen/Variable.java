package lum.compiler.codegen;

import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.constant.ConstantDesc;
import java.util.function.Consumer;

public interface Variable {
    int getSlot();
    TypeModel getType();
    CodeMaker codeMaker();

    default void load() {
        load(codeMaker());
    }
    void load(CodeMaker to);
    void store();

    Variable set(ConstantDesc value);
    Variable set(Variable value);
    Variable set(Consumer<CodeMaker> expression);

    Field field(String fieldName);

    Variable invoke(MethodModel method, Variable... arguments);
    Variable invoke(String methodName, Variable... arguments);
    Variable invoke(MethodModel method, ConstantDesc... arguments);
    Variable invoke(String methodName, ConstantDesc... arguments);

    Variable arrayLength();
    Variable arrayAccess(Variable[] arguments);
    Variable arrayAccess(Variable value, Variable[] at);

    Variable multiply(Variable other);
    Variable divide(Variable other);
    Variable intDiv(Variable other);
    Variable mod(Variable other);

    Variable add(Variable other);
    Variable sub(Variable other);

    Variable shr(Variable other);
    Variable shl(Variable other);

    Variable bitAnd(Variable other);
    Variable xor(Variable other);
    Variable bitOr(Variable other);

    Variable gt(Variable other);
    Variable lt(Variable other);
    Variable ge(Variable other);
    Variable le(Variable other);
    Variable eq(Variable other);
    Variable neq(Variable other);
    Variable isInstance(Variable other);
    Variable in(Variable other);

    Variable and(Variable other);
    Variable or(Variable other);

    Variable increment(Variable other);
    Variable decrement(Variable other);
    Variable not();
}
