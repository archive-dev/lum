package lum.compiler.jvm;

import lum.compiler.bytecode.CodeMaker;
import lum.compiler.bytecode.InlinedVariable;
import lum.compiler.bytecode.Variable;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.classfile.*;
import java.lang.classfile.instruction.BranchInstruction;
import java.lang.constant.ConstantDesc;
import java.lang.reflect.AccessFlag;
import java.util.HashMap;
import java.util.function.Consumer;

public class JVMCodeMaker implements CodeMaker<String> {
    private final HashMap<String, Variable> variables = new HashMap<>();
    private int lastSlot = 0;
    private final CodeBuilder cb;

    public JVMCodeMaker(CodeBuilder cb) {
        this.cb = cb;
    }

    @Override
    public CodeMaker<String> with(CodeElement codeElement) {
        cb.with(codeElement);
        return this;
    }

    @Override
    public Variable invoke(Variable caller, MethodModel method) {
        Opcode opcode;
        if (method.accessFlags().contains(AccessFlag.STATIC))
            opcode = Opcode.INVOKESTATIC;
        else if (method.owner().isInterface())
            opcode = Opcode.INVOKEINTERFACE;
        else if (method.accessFlags().contains(AccessFlag.PRIVATE) || method.name().equals("<init>"))
            opcode = Opcode.INVOKESPECIAL;
        else opcode = Opcode.INVOKEVIRTUAL;

        cb.loadLocal(caller.type().typeKind(), caller.slot());

        cb.invoke(opcode, method.owner().classDesc(), method.name(), method.methodTypeDesc(), caller.type().model().isInterface());
        var v = createVariable(method.returnType());
        v.store();
        return v;
    }

    @Override
    public Variable invoke(Variable caller, MethodModel method, ConstantDesc... arguments) {
        Opcode opcode;
        if (method.accessFlags().contains(AccessFlag.STATIC))
            opcode = Opcode.INVOKESTATIC;
        else if (method.owner().isInterface())
            opcode = Opcode.INVOKEINTERFACE;
        else if (method.accessFlags().contains(AccessFlag.PRIVATE) || method.name().equals("<init>"))
            opcode = Opcode.INVOKESPECIAL;
        else opcode = Opcode.INVOKEVIRTUAL;

        cb.loadLocal(caller.type().typeKind(), caller.slot());

        for (var arg : arguments) {
            load(arg);
        }

        cb.invoke(opcode, method.owner().classDesc(), method.name(), method.methodTypeDesc(), caller.type().model().isInterface());
        var v = createVariable(method.returnType());
        v.store();
        return v;
    }

    @Override
    public Variable invoke(Variable caller, MethodModel method, Variable... arguments) {
        Opcode opcode;
        if (method.accessFlags().contains(AccessFlag.STATIC))
            opcode = Opcode.INVOKESTATIC;
        else if (method.owner().isInterface())
            opcode = Opcode.INVOKEINTERFACE;
        else if (method.accessFlags().contains(AccessFlag.PRIVATE) || method.name().equals("<init>"))
            opcode = Opcode.INVOKESPECIAL;
        else opcode = Opcode.INVOKEVIRTUAL;

        cb.loadLocal(caller.type().typeKind(), caller.slot());

        for (var arg : arguments) {
            load(arg);
        }

        cb.invoke(opcode, method.owner().classDesc(), method.name(), method.methodTypeDesc(), caller.type().model().isInterface());
        var v = createVariable(method.returnType());
        v.store();
        return v;
    }

    @Override
    public Variable getVariable(String key) {
        return null;
    }

    @Override
    public Variable createVariable(String key, TypeModel initialType) {
        Variable var = new JVMVariable(this, cb, lastSlot++, initialType);
        variables.put(key, var);
        return var;
    }

    @Override
    public Variable createVariable(String key, ConstantDesc value) {
        Variable var = new JVMVariable(this, cb, lastSlot++, TypeModel.of(value.getClass()));
        variables.put(key, var);
        load(value);
        store(var);
        return var;
    }

    @Override
    public Variable createVariable(String key) {
        Variable var = new JVMVariable(this, cb, lastSlot++);
        variables.put(key, var);
        return var;
    }

    // TODO
    /// Creates instance of InlinedVariable
    @Override
    public InlinedVariable createVariable(TypeModel initialType) {
        return null;
    }

    /// Creates instance of InlinedVariable
    @Override
    public InlinedVariable createVariable(ConstantDesc value) {
        return null;
    }

    /// Creates instance of InlinedVariable
    @Override
    public InlinedVariable createVariable() {
        return null;
    }

    @Override
    public Label createLabel() {
        return cb.newLabel();
    }

    @Override
    public Label createBoundLabel() {
        return cb.newBoundLabel();
    }

    @Override
    public void bindLabel(Label label) {
        cb.labelBinding(label);
    }

    @Override
    public void ifThen(Variable left, Variable right, Opcode opcode, Consumer<CodeMaker<String>> thenHandler) {
        left.load();
        right.load();
        cb.ifThen(opcode, _ -> thenHandler.accept(this));
    }

    @Override
    public void ifThenElse(Variable left, Variable right, Opcode opcode, Consumer<CodeMaker<String>> thenHandler, Consumer<CodeMaker<String>> elseHandler) {
        left.load();
        right.load();

        cb.ifThenElse(opcode,
                _ -> thenHandler.accept(this),
                _ -> elseHandler.accept(this)
        );
    }

    @Override
    public TypeModel load(ConstantDesc constant) {
        return null;
    }

    @Override
    public TypeModel load(Variable variable) {
        return null;
    }

    @Override
    public void store(Variable variable) {

    }

    @Override
    public Variable arrayAccess(Variable array, ConstantDesc... arguments) {
        return null;
    }

    @Override
    public Variable arrayAccess(Variable array, Variable... arguments) {
        return null;
    }

    @Override
    public Variable increment(Variable variable) {
        return null;
    }

    @Override
    public Variable increment(Variable variable, ConstantDesc value) {
        return null;
    }

    @Override
    public Variable increment(Variable variable, Variable value) {
        return null;
    }

    @Override
    public Variable decrement(Variable variable) {
        return null;
    }

    @Override
    public Variable decrement(Variable variable, ConstantDesc value) {
        return null;
    }

    @Override
    public Variable decrement(Variable variable, Variable value) {
        return null;
    }

    @Override
    public Variable not(Variable variable) {
        return null;
    }

    @Override
    public Variable and(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable xor(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable or(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable gt(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable lt(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable ge(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable le(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable eq(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable neq(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable isInstance(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable in(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable shr(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable shl(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable add(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable subtract(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable multiply(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable divide(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable remainder(Variable left, Variable right) {
        return null;
    }

    @Override
    public Variable cast(Variable from, TypeModel to) {
        return null;
    }

    @Override
    public TypeModel arrayLoad(Variable array) {
        return null;
    }

    @Override
    public void arrayStore(Variable array) {

    }

    @Override
    public TypeModel arrayLength(TypeModel arrayType) {
        return null;
    }

    @Override
    public TypeModel newArray(TypeModel arrayType) {
        return null;
    }

    @Override
    public void returnVoid() {

    }

    @Override
    public TypeModel returnValue(TypeModel type) {
        return null;
    }

    @Override
    public void throwException() {

    }
}
