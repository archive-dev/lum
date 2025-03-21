package lum.compiler.codegen.jvm;

import lum.compiler.codegen.CodeMaker;
import lum.compiler.codegen.Field;
import lum.compiler.codegen.Variable;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.Opcode;
import java.lang.classfile.instruction.ConvertInstruction;
import java.lang.classfile.instruction.OperatorInstruction;
import java.lang.classfile.instruction.StackInstruction;
import java.lang.constant.ConstantDesc;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

class JVMVariable implements Variable {
    private static final HashMap<String, String> operatorNames = new HashMap<>();

    static {
        operatorNames.put("+", "add");
        operatorNames.put("-", "sub");
        operatorNames.put("*", "mul");
        operatorNames.put("/", "divide");
        operatorNames.put("//", "div");
        operatorNames.put("%", "mod");
        operatorNames.put("<<", "shl");
        operatorNames.put(">>", "shr");
        operatorNames.put(">", "gt");
        operatorNames.put(">=", "ge");
        operatorNames.put("==", "equals");
        operatorNames.put("<=", "le");
        operatorNames.put("<", "lt");
        operatorNames.put("++", "inc");
        operatorNames.put("--", "dec");
        operatorNames.put("&&", "and");
        operatorNames.put("&", "and");
        operatorNames.put("^", "xor");
        operatorNames.put("||", "or");
        operatorNames.put("|", "or");
        operatorNames.put("in", "contains");
        operatorNames.put("!", "not");
    }

    protected final int slot;
    protected TypeModel type = TypeModel.of(Object.class);
    private final CodeMaker codeMaker;

    public JVMVariable(int slot, TypeModel type, CodeMaker codeMaker) {
        this.slot = slot;
        this.type = type;
        this.codeMaker = codeMaker;
    }

    public JVMVariable(int slot, CodeMaker codeMaker) {
        this.slot = slot;
        this.codeMaker = codeMaker;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public TypeModel getType() {
        return type;
    }

    @Override
    public CodeMaker codeMaker() {
        return codeMaker;
    }

    @Override
    public void store() {
        store(getType());
    }

    private void store(TypeModel as) {
        codeMaker().codeBuilder().storeLocal(as.typeKind(), getSlot());
        this.type = as;
    }

    @Override
    public void load(CodeMaker to) {
        to.codeBuilder().loadLocal(getType().typeKind(), getSlot());
    }

    @Override
    public Variable set(TypeModel newType, ConstantDesc value) {
        codeMaker().load(value);
        store(newType);
        return this;
    }

    @Override
    public Variable set(TypeModel newType, Variable value) {
        value.load(codeMaker());
        store(newType);
        return this;
    }

    @Override
    public Variable set(TypeModel newType, Consumer<CodeMaker> expression) {
        expression.accept(codeMaker());
        store(newType);
        return this;
    }

    @Override
    public Field field(String fieldName) {
        var field = getType().model().getField(fieldName);
        Field jvmField = new JVMField(codeMaker(), this, field.owner(), field);
        return jvmField;
    }

    private Opcode getInvokeOpcode(MethodModel method) {
        Opcode invokeOpcode = Opcode.INVOKEVIRTUAL;

        if (method.isStatic())
            invokeOpcode = Opcode.INVOKESTATIC;
        else if (getType().model().isInterface())
            invokeOpcode = Opcode.INVOKEINTERFACE;
        else if (type.model().isSubclassOf(method.owner()))
            invokeOpcode = Opcode.INVOKESPECIAL;

        return invokeOpcode;
    }

    @Override
    public Variable invoke(MethodModel method, Variable... arguments) {
        Opcode opcode = getInvokeOpcode(method);

        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (opcode != Opcode.INVOKESTATIC)
                load();

            for (var arg : arguments) {
                cm.load(arg);
            }

            cm.codeBuilder().invoke(opcode, getType().classDesc(), method.name(), method.methodTypeDesc(), getType().model().isInterface());
        });
        v.setType(method.returnType());

        return v.build();
    }

    private MethodModel getOperatorMethod(String operatorName, Variable... arguments) {
        var args = Arrays.stream(arguments).map(Variable::getType).toList();
        var model = getType().model();

        if (model.getMethod(operatorName, args) != null) {
            return model.getMethod(operatorName, args);
        } else if (model.getMethod(operatorNames.get(operatorName), args) != null) {
            return model.getMethod(operatorNames.get(operatorName), args);
        }

        return null;
    }

    private Variable invokeOperator(String operatorName, Variable... arguments) {
        var args = Arrays.stream(arguments).map(Variable::getType).toList();
        var model = getType().model();

        if (model.getMethod(operatorName, args) != null) {
            return invoke(operatorName, arguments);
        } else if (model.getMethod(operatorNames.get(operatorName), args) != null) {
            return invoke(operatorNames.get(operatorName), arguments);
        }

        throw new IllegalStateException();
    }

    @Override
    public Variable invoke(String methodName, Variable... arguments) {
        var method = getType().model().getMethod(methodName, Arrays.stream(arguments).map(Variable::getType).toList());

        Opcode opcode = getInvokeOpcode(method);

        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (opcode != Opcode.INVOKESTATIC)
                load(cm);

            for (var arg : arguments) {
                cm.load(arg);
            }

            cm.codeBuilder().invoke(opcode, getType().classDesc(), method.name(), method.methodTypeDesc(), getType().model().isInterface());
        });
        v.setType(method.returnType());

        return v.build();
    }

    @Override
    public Variable invoke(MethodModel method, ConstantDesc... arguments) {
        Opcode opcode = getInvokeOpcode(method);

        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (opcode != Opcode.INVOKESTATIC)
                load(cm);

            for (var arg : arguments) {
                cm.load(arg);
            }

            cm.codeBuilder().invoke(opcode, getType().classDesc(), method.name(), method.methodTypeDesc(), getType().model().isInterface());
        });
        v.setType(method.returnType());

        return v.build();
    }

    @Override
    public Variable invoke(String methodName, ConstantDesc... arguments) {
        var method = getType().model().getMethod(
                methodName,
                Arrays.stream(arguments)
                        .map(ConstantDesc::getClass)
                        .map(TypeModel::of)
                        .toList()
        );

        Opcode opcode = getInvokeOpcode(method);

        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (opcode != Opcode.INVOKESTATIC)
                this.load(cm);

            for (var arg : arguments) {
                cm.load(arg);
            }

            cm.codeBuilder().invoke(opcode, getType().classDesc(), method.name(), method.methodTypeDesc(), getType().model().isInterface());
        });
        v.setType(method.returnType());

        return v.build();
    }

    @Override
    public Variable arrayLength() {
        if (!getType().isArray()) return null;
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            this.load(cm);
            cm.codeBuilder().arraylength();
        }).setType(TypeModel.INT).setCodeMaker(codeMaker());

        return v.build();
    }

    @Override
    public Variable arrayAccess(Variable[] arguments) {
        if (getType().isArray()) {
            if (arguments.length != 1) throw new IllegalStateException();
            return new JVMArrayElement(cm -> cm.load(arguments[0]), this);
        }

        MethodModel model = getType().model().getMethod("[]", Arrays.stream(arguments).map(Variable::getType).toList());
        if (model == null) {
            model = getType().model().getMethod("get", Arrays.stream(arguments).map(Variable::getType).toList());
        }

        return invoke(model, arguments);
    }

    @Override
    public void arrayAccess(Variable value, Variable[] at) {
        arrayAccess(at).set(value);
    }

    private Variable handleArithmeticOrBitwiseOperation(Variable other, Opcode opcode, String operatorName) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (opcode != null || getType().isPrimitive()) {
                load();
                other.load(cm);
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                cb.with(OperatorInstruction.of(opcode));
            } else {
                invokeOperator(operatorName, other).load(cm);
            }
        });

        if (opcode != null) {
            v.setType(TypeModel.of(opcode.primaryTypeKind()));
        } else {
            v.setType(getOperatorMethod(operatorName, other).returnType());
        }
        v.setCodeMaker(codeMaker());

        return v.build();
    }

    @Override
    public Variable multiply(Variable other) {
        return handleArithmeticOrBitwiseOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IMUL;
            case LongType -> Opcode.LMUL;
            case FloatType -> Opcode.FMUL;
            case DoubleType -> Opcode.DMUL;
            case ReferenceType, VoidType -> null;
        }, "*");
    }

    @Override
    public Variable divide(Variable other) {
        return handleArithmeticOrBitwiseOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IDIV;
            case LongType -> Opcode.LDIV;
            case FloatType -> Opcode.FDIV;
            case DoubleType -> Opcode.DDIV;
            case ReferenceType, VoidType -> null;
        }, "/");
    }

    @Override
    public Variable intDiv(Variable other) {
        var v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (getType().isPrimitive()) {
                load(cm);
                other.load(cm);
                sub(mod(other));
            } else {
                invokeOperator("//", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable mod(Variable other) {
        return handleArithmeticOrBitwiseOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IREM;
            case LongType -> Opcode.LREM;
            case FloatType -> Opcode.FREM;
            case DoubleType -> Opcode.DREM;
            case ReferenceType, VoidType -> null;
        }, "%");
    }

    @Override
    public Variable add(Variable other) {
        return handleArithmeticOrBitwiseOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IADD;
            case LongType -> Opcode.LADD;
            case FloatType -> Opcode.FADD;
            case DoubleType -> Opcode.DADD;
            case ReferenceType, VoidType -> null;
        }, "+");
    }

    @Override
    public Variable sub(Variable other) {
        return handleArithmeticOrBitwiseOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.ISUB;
            case LongType -> Opcode.LSUB;
            case FloatType -> Opcode.FSUB;
            case DoubleType -> Opcode.DSUB;
            case ReferenceType, VoidType -> null;
        }, "-");
    }

    @Override
    public Variable shr(Variable other) {
        return handleShiftOperation(other, Opcode.ISHR, Opcode.LSHR, ">>");
    }

    @Override
    public Variable shl(Variable other) {
        return handleShiftOperation(other, Opcode.ISHL, Opcode.LSHL, "<<");
    }

    private Variable handleShiftOperation(Variable other, Opcode intOpcode, Opcode longOpcode, String operatorName) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        final TypeModel[] type = new TypeModel[1];
        v.addCode(cm -> {
            load();
            other.load(cm);

            if (getType().isPrimitive()) {
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                TypeModel currentType = getType();
                if (getType().equals(TypeModel.of(float.class)) || getType().equals(TypeModel.of(double.class))) {
                    cb.with(StackInstruction.of(Opcode.SWAP));
                    cb.with(ConvertInstruction.of(getType().typeKind(), TypeModel.of(long.class).typeKind()));
                    currentType = TypeModel.of(long.class);
                    cb.with(StackInstruction.of(Opcode.SWAP));
                }

                cb.with(OperatorInstruction.of(switch (currentType.typeKind().asLoadable()) {
                    case IntType -> {
                        type[0] = TypeModel.INT;
                        yield intOpcode;
                    }
                    case LongType -> {
                        type[0] = TypeModel.LONG;
                        yield longOpcode;
                    }
                    default -> throw new IllegalStateException();
                }));
            } else {
                invokeOperator(operatorName, other);
            }
        });

        if (type[0] != null) {
            v.setType(type[0]);
        } else {
            v.setType(getOperatorMethod(operatorName, other).returnType());
        }
        v.setCodeMaker(codeMaker());

        return v.build();
    }

    @Override
    public Variable bitAnd(Variable other) {
        return and(other);
    }

    @Override
    public Variable xor(Variable other) {
        return handleBitwiseOperation(other, Opcode.IXOR, Opcode.LXOR, "^");
    }

    @Override
    public Variable bitOr(Variable other) {
        return or(other);
    }

    private Variable handleBitwiseOperation(Variable other, Opcode intOpcode, Opcode longOpcode, String operatorName) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (getType().isPrimitive()) {
                load();
                other.load(cm);
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                TypeModel currentType = getType();
                if (getType().equals(TypeModel.of(float.class)) || getType().equals(TypeModel.of(double.class))) {
                    cb.with(StackInstruction.of(Opcode.SWAP));
                    cb.with(ConvertInstruction.of(getType().typeKind(), TypeModel.of(long.class).typeKind()));
                    currentType = TypeModel.of(long.class);
                    cb.with(StackInstruction.of(Opcode.SWAP));
                }

                cb.with(OperatorInstruction.of(switch (currentType.typeKind().asLoadable()) {
                    case IntType -> intOpcode;
                    case LongType -> longOpcode;
                    default -> throw new IllegalStateException();
                }));
            } else {
                invokeOperator(operatorName, other);
            }
        });

        return v.build();
    }

    @Override
    public Variable gt(Variable other) {
        return handleComparisonOperation(other, Opcode.IF_ICMPGT, 1, ">");
    }

    @Override
    public Variable lt(Variable other) {
        return handleComparisonOperation(other, Opcode.IF_ICMPLT, -1, "<");
    }

    @Override
    public Variable ge(Variable other) {
        return handleComparisonOperation(other, Opcode.IF_ICMPGE, 0, ">=");
    }

    @Override
    public Variable le(Variable other) {
        return handleComparisonOperation(other, Opcode.IF_ICMPLE, 0, "<=");
    }

    @Override
    public Variable eq(Variable other) {
        return handleComparisonOperation(other, Opcode.IF_ICMPEQ, 0, "==");
    }

    @Override
    public Variable neq(Variable other) {
        return handleComparisonOperation(other, Opcode.IF_ICMPNE, 0, "!=");
    }

    private Variable handleComparisonOperation(Variable other, Opcode opcode, int cmpValue, String operatorName) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (getType().isPrimitive()) {
                load();
                other.load(cm);
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                switch (getType().typeKind().asLoadable()) {
                    case IntType -> cb.ifThenElse(opcode, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
                    case LongType -> {
                        cb.lcmp();
                        cb.loadConstant(cmpValue);
                    }
                    case FloatType -> {
                        cb.fcmpg();
                        cb.loadConstant(cmpValue);
                    }
                    case DoubleType -> {
                        cb.dcmpg();
                        cb.loadConstant(cmpValue);
                    }
                }
                cb.ifThenElse(Opcode.IFEQ, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
            } else {
                invokeOperator(operatorName, other);
            }
        });

        if (opcode != null) {
            v.setType(TypeModel.of(opcode.primaryTypeKind()));
        } else {
            v.setType(getOperatorMethod(operatorName, other).returnType());
        }
        v.setCodeMaker(codeMaker());

        return v.build();
    }

    @Override
    public Variable isInstance(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(_ -> {
            if (getType().isPrimitive()) {
                load();
                var cb = codeMaker().codeBuilder();
                cb.instanceOf(other.getType().classDesc());
            } else {
                invokeOperator("is", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable in(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(_ -> {
            if (getType().isPrimitive()) {
                throw new IllegalStateException();
            } else {
                invokeOperator("in", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable and(Variable other) {
        return handleLogicalOperation(other, Opcode.IAND, Opcode.LAND, "&&");
    }

    @Override
    public Variable or(Variable other) {
        return handleLogicalOperation(other, Opcode.IOR, Opcode.LOR, "||");
    }

    private Variable handleLogicalOperation(Variable other, Opcode intOpcode, Opcode longOpcode, String operatorName) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            load();
            other.load(cm);

            if (getType().isPrimitive()) {
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                TypeModel currentType = getType();
                if (getType().equals(TypeModel.of(float.class)) || getType().equals(TypeModel.of(double.class))) {
                    cb.with(StackInstruction.of(Opcode.SWAP));
                    cb.with(ConvertInstruction.of(getType().typeKind(), TypeModel.of(long.class).typeKind()));
                    currentType = TypeModel.of(long.class);
                    cb.with(StackInstruction.of(Opcode.SWAP));
                }

                cb.with(OperatorInstruction.of(switch (currentType.typeKind()) {
                    case IntType, BooleanType, CharType, ByteType, ShortType -> intOpcode;
                    case LongType -> longOpcode;
                    case FloatType, DoubleType, ReferenceType, VoidType -> throw new IllegalStateException();
                }));
            } else {
                invokeOperator(operatorName, other);
            }
        });

        return v.build();
    }

    @Override
    public Variable increment(Variable other) {
        return handleIncrementDecrement(other, this::add, "++");
    }

    @Override
    public Variable decrement(Variable other) {
        return handleIncrementDecrement(other, this::sub, "--");
    }

    private Variable handleIncrementDecrement(Variable other, Operation operation, String operatorName) {
        if (getType().isPrimitive()) {
            Variable incrementValue = (other == null) ? codeMaker().var(1) : other;
            return set(operation.apply(incrementValue));
        } else {
            return new JVMInlinedVariableBuilder(List.of(_ -> invokeOperator(operatorName, other)))
                    .setType(getOperatorMethod(operatorName, other).returnType()).build();
        }
    }

    @Override
    public Variable not() {
        if (getType().isPrimitive()) {
            return xor(codeMaker().var(-1));
        }

        return new JVMInlinedVariableBuilder(List.of((_) -> invokeOperator("!"))).build();
    }

    @Override
    public Variable isInstance(TypeModel type) {
        var builder = new JVMInlinedVariableBuilder();
        builder.addCode(cm -> {
            load(cm);
            cm.codeBuilder().instanceOf(type.classDesc());
        })
                .setType(TypeModel.BOOLEAN)
                .setCodeMaker(this.codeMaker());

        return builder.build();
    }

    @Override
    public Variable cast(TypeModel type) {
        load();
        codeMaker().codeBuilder().checkcast(type.classDesc());
        this.type = type;
        return this;
    }

    @FunctionalInterface
    private interface Operation {
        Variable apply(Variable variable);
    }
}