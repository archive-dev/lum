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
    public Variable set(ConstantDesc value) {
        codeMaker().load(value);
        store(TypeModel.of(value.getClass()));
        return this;
    }

    @Override
    public Variable set(Variable value) {
        value.load();
        store(value.getType());

        return this;
    }

    @Override
    public Variable set(Consumer<CodeMaker> expression) {
        expression.accept(codeMaker());
        return this;
    }

    @Override
    public Field field(String fieldName) {
        var field = getType().model().getField(fieldName);

        Field jvmField = new JVMField(codeMaker(), this, field.owner(), field);
        jvmField.set((_) -> {
            if (!field.isStatic()) {
                codeMaker().codeBuilder().getfield(
                        getType().classDesc(),
                        fieldName,
                        field.type().classDesc()
                );
            } else {
                codeMaker().codeBuilder().getstatic(
                        getType().classDesc(),
                        fieldName,
                        field.type().classDesc()
                );
            }
        });
        return jvmField; // todo
    }

    private Opcode getInvokeOpcode(MethodModel method) {
        Opcode invokeOpcode = Opcode.INVOKEVIRTUAL;

        if (method.isStatic())
            invokeOpcode = Opcode.INVOKESTATIC;
        else if (type.model().isSubclassOf(method.owner()))
            invokeOpcode = Opcode.INVOKESPECIAL;
        else if (getType().model().isInterface()) {
            invokeOpcode = Opcode.INVOKEINTERFACE;
        }

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

        return v.build();
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
                load();

            for (var arg : arguments) {
                cm.load(arg);
            }

            cm.codeBuilder().invoke(opcode, getType().classDesc(), method.name(), method.methodTypeDesc(), getType().model().isInterface());
        });

        return v.build();
    }

    @Override
    public Variable invoke(MethodModel method, ConstantDesc... arguments) {
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

        return v.build();
    }

    @Override
    public Variable arrayLength() {
        if (!getType().isArray()) return null;
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
                this.load(cm);
                cm.codeBuilder().arraylength();
            }
        );

        return v.build();
    }

    @Override
    public Variable arrayAccess(Variable[] arguments) {
        MethodModel model = getType().model().getMethod("[]", Arrays.stream(arguments).map(Variable::getType).toList());
        if (model == null) {
            model = getType().model().getMethod("get", Arrays.stream(arguments).map(Variable::getType).toList());
        }

        return invoke(model, arguments);
    }

    @Override
    public Variable arrayAccess(Variable value, Variable[] at) {
        Variable[] arguments = new Variable[at.length+1];
        arguments[0] = value;
        System.arraycopy(at, 1, arguments, 1, at.length);

        MethodModel model = getType().model().getMethod("[]", Arrays.stream(arguments).map(Variable::getType).toList());
        if (model == null) {
            model = getType().model().getMethod("set", Arrays.stream(arguments).map(Variable::getType).toList());
        }

        return invoke(model, arguments);
    }

    private Variable arithmeticOperation(Variable other, Opcode opcode, String operatorName) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (getType().isPrimitive()) {
                load();
                other.load();
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                cb.with(OperatorInstruction.of(opcode));
            } else {
                invokeOperator(operatorName, other);
            }
        });

        return v.build();
    }

    @Override
    public Variable multiply(Variable other) {
        return arithmeticOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IMUL;
            case LongType -> Opcode.LMUL;
            case FloatType -> Opcode.FMUL;
            case DoubleType -> Opcode.DMUL;
            case ReferenceType, VoidType -> throw new IllegalStateException();
        }, "*");
    }

    @Override
    public Variable divide(Variable other) {
        return arithmeticOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IDIV;
            case LongType -> Opcode.LDIV;
            case FloatType -> Opcode.FDIV;
            case DoubleType -> Opcode.DDIV;
            case ReferenceType, VoidType -> throw new IllegalStateException();
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
        return arithmeticOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IREM;
            case LongType -> Opcode.LREM;
            case FloatType -> Opcode.FREM;
            case DoubleType -> Opcode.DREM;
            case ReferenceType, VoidType -> throw new IllegalStateException();
        }, "%");
    }

    @Override
    public Variable add(Variable other) {
        return arithmeticOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IADD;
            case LongType -> Opcode.LADD;
            case FloatType -> Opcode.FADD;
            case DoubleType -> Opcode.DADD;
            case ReferenceType, VoidType -> throw new IllegalStateException();
        }, "+");
    }

    @Override
    public Variable sub(Variable other) {
        return arithmeticOperation(other, switch (getType().typeKind()) {
            case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.ISUB;
            case LongType -> Opcode.LSUB;
            case FloatType -> Opcode.FSUB;
            case DoubleType -> Opcode.DSUB;
            case ReferenceType, VoidType -> throw new IllegalStateException();
        }, "-");
    }


    @Override
    public Variable shr(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            load();
            other.load();

            if (getType().isPrimitive()) {
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                type = getType();
                if (getType().equals(TypeModel.of(float.class)) || getType().equals(TypeModel.of(double.class))) {
                    cb.with(StackInstruction.of(Opcode.SWAP));
                    cb.with(ConvertInstruction.of(getType().typeKind(), TypeModel.of(long.class).typeKind()));
                    type = TypeModel.of(long.class);
                    cb.with(StackInstruction.of(Opcode.SWAP));
                }

                cb.with(OperatorInstruction.of(switch (type.typeKind().asLoadable()) {
                    case IntType -> Opcode.ISHR;
                    case LongType -> Opcode.LSHR;
                    default -> throw new IllegalStateException();
                }));
            } else {
                invokeOperator(">>", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable shl(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            load();
            other.load();

            if (getType().isPrimitive()) {
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                type = getType();
                if (getType().equals(TypeModel.of(float.class)) || getType().equals(TypeModel.of(double.class))) {
                    cb.with(StackInstruction.of(Opcode.SWAP));
                    cb.with(ConvertInstruction.of(getType().typeKind(), TypeModel.of(long.class).typeKind()));
                    type = TypeModel.of(long.class);
                    cb.with(StackInstruction.of(Opcode.SWAP));
                }

                cb.with(OperatorInstruction.of(switch (type.typeKind().asLoadable()) {
                    case IntType -> Opcode.ISHL;
                    case LongType -> Opcode.LSHL;
                    default -> throw new IllegalStateException();
                }));
            } else {
                invokeOperator("<<", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable bitAnd(Variable other) {
        return and(other);
    }

    @Override
    public Variable xor(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            if (getType().isPrimitive()) {
                load();
                other.load();
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                type = getType();
                if (getType().equals(TypeModel.of(float.class)) || getType().equals(TypeModel.of(double.class))) {
                    cb.with(StackInstruction.of(Opcode.SWAP));
                    cb.with(ConvertInstruction.of(getType().typeKind(), TypeModel.of(long.class).typeKind()));
                    type = TypeModel.of(long.class);
                    cb.with(StackInstruction.of(Opcode.SWAP));
                }

                cb.with(OperatorInstruction.of(switch (type.typeKind().asLoadable()) {
                    case IntType -> Opcode.IXOR;
                    case LongType -> Opcode.LXOR;
                    default -> throw new IllegalStateException();
                }));
            } else {
                invokeOperator("^", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable bitOr(Variable other) {
        return or(other);
    }

    @Override
    public Variable gt(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            int cmp = 1;

            if (getType().isPrimitive()) {
                load();
                other.load();
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                switch (getType().typeKind().asLoadable()) {
                    case IntType -> cb.ifThenElse(Opcode.IF_ICMPGT, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
                    case LongType -> {
                        cb.lcmp();
                        cb.ldc(cmp);
                    }
                    case FloatType -> {
                        cb.fcmpg();
                        cb.ldc(cmp);
                    }
                    case DoubleType -> {
                        cb.dcmpg();
                        cb.ldc(cmp);
                    }
                }
                cb.ifThenElse(Opcode.IFEQ, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
            } else {
                invokeOperator(">", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable lt(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            int cmp = -1;

            if (getType().isPrimitive()) {
                load();
                other.load();
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                switch (getType().typeKind().asLoadable()) {
                    case IntType -> cb.ifThenElse(Opcode.IF_ICMPLT, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
                    case LongType -> {
                        cb.lcmp();
                        cb.ldc(cmp);
                    }
                    case FloatType -> {
                        cb.fcmpg();
                        cb.ldc(cmp);
                    }
                    case DoubleType -> {
                        cb.dcmpg();
                        cb.ldc(cmp);
                    }
                }
                cb.ifThenElse(Opcode.IFEQ, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
            } else {
                invokeOperator("<", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable ge(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            int cmp = 0;

            if (getType().isPrimitive()) {
                load();
                other.load();
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                switch (getType().typeKind().asLoadable()) {
                    case IntType -> cb.ifThenElse(Opcode.IF_ICMPGE, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
                    case LongType -> {
                        cb.lcmp();
                        cb.ldc(cmp);
                    }
                    case FloatType -> {
                        cb.fcmpg();
                        cb.ldc(cmp);
                    }
                    case DoubleType -> {
                        cb.dcmpg();
                        cb.ldc(cmp);
                    }
                }
                cb.ifThenElse(Opcode.IF_ICMPGE, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
            } else {
                invokeOperator(">=", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable le(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            int cmp = 0;

            if (getType().isPrimitive()) {
                load();
                other.load();
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                switch (getType().typeKind().asLoadable()) {
                    case IntType -> cb.ifThenElse(Opcode.IF_ICMPLE, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
                    case LongType -> {
                        cb.lcmp();
                        cb.ldc(cmp);
                    }
                    case FloatType -> {
                        cb.fcmpg();
                        cb.ldc(cmp);
                    }
                    case DoubleType -> {
                        cb.dcmpg();
                        cb.ldc(cmp);
                    }
                }
                cb.ifThenElse(Opcode.IF_ICMPLE, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
            } else {
                invokeOperator("<=", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable eq(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            int cmp = 0;

            if (getType().isPrimitive()) {
                load();
                other.load();
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                switch (getType().typeKind().asLoadable()) {
                    case IntType -> cb.ifThenElse(Opcode.IF_ICMPEQ, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
                    case LongType -> {
                        cb.lcmp();
                        cb.ldc(cmp);
                    }
                    case FloatType -> {
                        cb.fcmpg();
                        cb.ldc(cmp);
                    }
                    case DoubleType -> {
                        cb.dcmpg();
                        cb.ldc(cmp);
                    }
                }
                cb.ifThenElse(Opcode.IF_ICMPEQ, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
            } else {
                invokeOperator("==", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable neq(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            int cmp = 0;

            if (getType().isPrimitive()) {
                load();
                other.load();
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                switch (getType().typeKind().asLoadable()) {
                    case IntType -> cb.ifThenElse(Opcode.IF_ICMPNE, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
                    case LongType -> {
                        cb.lcmp();
                        cb.ldc(cmp);
                    }
                    case FloatType -> {
                        cb.fcmpg();
                        cb.ldc(cmp);
                    }
                    case DoubleType -> {
                        cb.dcmpg();
                        cb.ldc(cmp);
                    }
                }
                cb.ifThenElse(Opcode.IF_ICMPNE, CodeBuilder::iconst_1, CodeBuilder::iconst_0);
            } else {
                invokeOperator("!=", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable isInstance(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
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
        v.addCode(cm -> {
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
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            load();
            other.load();

            if (getType().isPrimitive()) {
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                type = getType();
                if (getType().equals(TypeModel.of(float.class)) || getType().equals(TypeModel.of(double.class))) {
                    cb.with(StackInstruction.of(Opcode.SWAP));
                    cb.with(ConvertInstruction.of(getType().typeKind(), TypeModel.of(long.class).typeKind()));
                    type = TypeModel.of(long.class);
                    cb.with(StackInstruction.of(Opcode.SWAP));
                }

                cb.with(OperatorInstruction.of(switch (type.typeKind()) {
                    case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IAND;
                    case LongType -> Opcode.LAND;
                    case FloatType, DoubleType, ReferenceType, VoidType -> throw new IllegalStateException();
                }));
            } else {
                invokeOperator("&&", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable or(Variable other) {
        JVMInlinedVariableBuilder v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            load();
            other.load();

            if (getType().isPrimitive()) {
                var cb = cm.codeBuilder();
                if (!other.getType().typeKind().asLoadable().equals(getType().typeKind().asLoadable()))
                    cb.with(ConvertInstruction.of(other.getType().typeKind(), getType().typeKind()));
                type = getType();
                if (getType().equals(TypeModel.of(float.class)) || getType().equals(TypeModel.of(double.class))) {
                    cb.with(StackInstruction.of(Opcode.SWAP));
                    cb.with(ConvertInstruction.of(getType().typeKind(), TypeModel.of(long.class).typeKind()));
                    type = TypeModel.of(long.class);
                    cb.with(StackInstruction.of(Opcode.SWAP));
                }

                cb.with(OperatorInstruction.of(switch (type.typeKind()) {
                    case IntType, BooleanType, CharType, ByteType, ShortType -> Opcode.IOR;
                    case LongType -> Opcode.LOR;
                    case FloatType, DoubleType, ReferenceType, VoidType -> throw new IllegalStateException();
                }));
            } else {
                invokeOperator("||", other);
            }
        });

        return v.build();
    }

    @Override
    public Variable increment(Variable other) {
        if (getType().isPrimitive()) {
            load();

            if (other == null) {
                return set(add(codeMaker().var(1)));
            }

            other.load();
            return set(add(other));
        } else {
            return new JVMInlinedVariableBuilder(List.of(_ -> invokeOperator("++", other))).build();
        }
    }

    @Override
    public Variable decrement(Variable other) {
        if (getType().isPrimitive()) {
            load();

            if (other == null) {
                return set(sub(codeMaker().var(1)));
            }

            other.load();
            return set(sub(other));
        } else {
            return new JVMInlinedVariableBuilder(List.of(_ -> invokeOperator("--", other))).build();
        }
    }

    @Override
    public Variable not() {
        if (getType().isPrimitive()) {
            return xor(codeMaker().var(-1));
        }

        return new JVMInlinedVariableBuilder(List.of((_) -> invokeOperator("!"))).build();
    }
}
