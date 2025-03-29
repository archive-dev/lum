package lum.compiler.codegen.jvm;

import lum.compiler.codegen.CodeMaker;
import lum.compiler.codegen.InlinedVariable;
import lum.compiler.codegen.InlinedVariableBuilder;
import lum.compiler.codegen.Variable;
import lum.core.model.ClassModel;
import lum.core.model.MethodModel;
import lum.core.model.ParameterModel;
import lum.core.model.TypeModel;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.Instruction;
import java.lang.classfile.instruction.NewPrimitiveArrayInstruction;
import java.lang.classfile.instruction.NewReferenceArrayInstruction;
import java.lang.classfile.instruction.SwitchCase;
import java.lang.constant.ConstantDesc;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

class JVMCodeMaker implements CodeMaker {
    private final HashMap<String, Variable> locals = new HashMap<>();
    private final HashMap<Integer, Variable> parameters = new HashMap<>();

    private final CodeBuilder.BlockCodeBuilder cb;
    private final MethodModel method;

    MethodModel method() {
        return method;
    }

    public JVMCodeMaker(CodeBuilder.BlockCodeBuilder cb, MethodModel method) {
        this.cb = cb;
        this.method = method;

        ParameterModel[] methodParameters = method.parameters();
        for (int i = 0; i < methodParameters.length; i++) {
            var param = methodParameters[i];
            parameters.put(i, new JVMVariable(i, param.type(), this));
        }

        if (!method.name().equals("<init>") && method.isStatic())
            return;

        locals.put("this",
                new JVMInlinedVariableBuilder()
                        .addCode(cm -> cm.codeBuilder().aload(0))
                        .setType(method.owner().typeModel())
                        .setCodeMaker(this).build()
        );
    }

    public JVMCodeMaker(CodeBuilder.BlockCodeBuilder cb, JVMCodeMaker parent) {
        this.cb = cb;
        this.method = parent.method;

        this.locals.putAll(parent.locals);
        this.parameters.putAll(parent.parameters);
    }

    @Override
    public CodeBuilder codeBuilder() {
        return cb;
    }

    @Override
    public Variable this_() {
        if (method.name().equals("<init>"))
            return var("this");
        if (method.isStatic())
            return null;
        return var("this");
    }

    @Override
    public Variable super_() {
        var v = new JVMInlinedVariableBuilder();
        TypeModel superClass = method.owner().superClass().typeModel();
        v.addCode(cm -> cm.this_().load(cm));
        v.setType(superClass);
        v.setCodeMaker(this);

        return v.build();
    }

    @Override
    public Variable parameter(int index) {
        return parameters.get(index);
    }

    @Override
    public CodeMaker ifThen(Variable value, final Consumer<CodeMaker> ifHandler) {
        value.load(this);
        cb.ifThen((b) -> ifHandler.accept(new JVMCodeMaker(b, this)));
        return this;
    }

    @Override
    public CodeMaker ifThenElse(Variable value, Consumer<CodeMaker> ifHandler, Consumer<CodeMaker> elseHandler) {
        value.load(this);
        cb.ifThenElse(
                (b) -> ifHandler.accept(new JVMCodeMaker(b, this)),
                (b) -> elseHandler.accept(new JVMCodeMaker(b, this))
        );
        return this;
    }

    @Override
    public CodeMaker switch_(Variable value, Map<Function<CodeMaker, Object>, Consumer<CodeMaker>> cases) {
        // Create labels for each case and the end of the switch
        var defaultLabel = codeBuilder().newLabel();
        var endLabel = codeBuilder().newLabel();

        // Collect all case labels and their handlers
        List<SwitchCase> switchCases = new ArrayList<>();
        Map<SwitchCase, Consumer<CodeMaker>> caseHandlers = new HashMap<>();

        // Process each case
        for (var entry : cases.entrySet()) {
            var caseLabel = codeBuilder().newLabel();

            // Create a temporary CodeMaker to capture the case value

            // Get the case value directly using the Function
            Object caseValue = entry.getKey().apply(this);

            // If the case value is a constant, add it as a switch case
            if (caseValue != null) {
                if (caseValue instanceof Integer intValue) {
                    SwitchCase switchCase = SwitchCase.of(intValue, caseLabel);
                    switchCases.add(switchCase);
                    caseHandlers.put(switchCase, entry.getValue());
                }
//                else if (caseValue instanceof Variable var && var.isConstant() && var.getConstantValue() instanceof Integer intValue) {
//                    SwitchCase switchCase = SwitchCase.of(intValue, caseLabel);
//                    switchCases.add(switchCase);
//                    caseHandlers.put(switchCase, entry.getValue());
//                }
                else {
                    // For non-integer constants, use if-else comparison
                    codeBuilder().dup();
                    if (caseValue instanceof Variable var) {
                        var.load(this);
                    } else if (caseValue instanceof ConstantDesc constant) {
                        codeBuilder().loadConstant(constant);
                    }
                    codeBuilder().if_icmpeq(caseLabel);
                    caseHandlers.put(null, entry.getValue()); // Use null as key for non-switch cases
                }
            } else {
                // For non-constant cases, use default case
                caseHandlers.put(null, entry.getValue());
            }
        }

        // If we have integer cases, generate a tableswitch or lookupswitch
        if (!switchCases.isEmpty()) {
            codeBuilder().tableswitch(defaultLabel, switchCases);

            // Generate code for each case
            for (var switchCase : switchCases) {
                codeBuilder().labelBinding(switchCase.target());
                Consumer<CodeMaker> handler = caseHandlers.get(switchCase);
                if (handler != null) {
                    handler.accept(this);
                }
                codeBuilder().goto_(endLabel);
            }
        }

        // Default case handling
        codeBuilder().labelBinding(defaultLabel);
        Consumer<CodeMaker> defaultHandler = caseHandlers.get(null);
        if (defaultHandler != null) {
            defaultHandler.accept(this);
        }

        // End of switch
        codeBuilder().labelBinding(endLabel);

        return this;
    }

    @Override
    public CodeMaker return_() {
        cb.return_();
        return this;
    }

    @Override
    public CodeMaker return_(Variable value) {
        value.load(this);
        cb.return_(method.returnType().typeKind());
        return this;
    }

    @Override
    public CodeMaker return_(ConstantDesc value) {
        cb.loadConstant(value);
        cb.return_(TypeModel.of(value.getClass()).typeKind());
        return this;
    }

    @Override
    public CodeMaker break_() {
        cb.goto_(cb.breakLabel());
        return this;
    }

    @Override
    public CodeMaker continue_() {
        cb.goto_(cb.startLabel());
        return this;
    }

    @Override
    public CodeMaker for_(Variable i, BiConsumer<CodeMaker, Variable> condition, BiConsumer<CodeMaker, Variable> update, BiConsumer<CodeMaker, Variable> block) {
        if (i instanceof InlinedVariable) {
            i = var("i"+i.hashCode()).set(i);
        }

        Variable finalI = i;
        cb.block((b) -> {
            var cm = new JVMCodeMaker(b, this);

            condition.accept(cm, finalI);
            b.ifne(b.breakLabel());

            block.accept(cm, finalI);

            update.accept(cm, finalI);
            b.goto_(b.startLabel());
        });

        return this;
    }
    @Override
    public CodeMaker foreach(Variable iterable, BiConsumer<CodeMaker, Variable> block) {
        if (iterable.getType().model().isSubclassOf(ClassModel.of(Iterable.class))) {
            Variable iterator = var("it"+iterable.hashCode()).set(iterable.invoke("iterator"));
            cb.block(b -> {
                var cm = new JVMCodeMaker(b, this);
                iterator.invoke("hasNext").load(cm);
                Variable i = var().addCode(_ -> iterator.invoke("next")).build();
                b.ifne(b.breakLabel());

                block.accept(cm, i);

                b.goto_(b.startLabel());
            });
        } else if (iterable.getType().isArray()) {
            for_(
                    var(0),
                    (cm, i) -> i.lt(iterable.arrayLength()).load(cm),
                    (cm, i) -> i.increment(cm.var(1)),
                    block
            );
        } else
            throw new IllegalStateException();

        return this;
    }

    @Override
    public CodeMaker foreach(Variable i, Variable iterable, BiConsumer<CodeMaker, Variable> block) {
        if (iterable.getType().model().isSubclassOf(ClassModel.of(Iterable.class))) {
            Variable iterator = var("it"+iterable.hashCode()).setTyped(iterable.invoke("iterator"));
            cb.block(b -> {
                var cm = new JVMCodeMaker(b, this);
                iterator.invoke("hasNext").load(cm);
                b.ifne(b.breakLabel());
                i.set(iterator.invoke("next"));

                block.accept(cm, i);

                b.goto_(b.startLabel());
            });
        } else if (iterable.getType().isArray()) {
            for_(
                    i,
                    (cm, i_) -> i_.lt(iterable.arrayLength()).load(cm),
                    (cm, i_) -> i_.increment(cm.var(1)),
                    block
            );
        } else if (iterable.getType().model().isSubclassOf(ClassModel.of(Map.class))) {
            return foreach(i, iterable.invoke("entrySet"), block);
        } else
            throw new IllegalStateException();

        return this;
    }

    @Override
    public CodeMaker while_(Consumer<CodeMaker> condition, Consumer<CodeMaker> block) {
        cb.block(b -> {
            var maker = new JVMCodeMaker(b, this);
            condition.accept(maker);
            b.ifeq(b.breakLabel());
            block.accept(maker);
            b.goto_(b.startLabel());
        });
        return this;
    }

    @Override
    public CodeMaker doWhile(Consumer<CodeMaker> condition, Consumer<CodeMaker> block) {
        cb.block(b -> {
            var maker = new JVMCodeMaker(b, this);
            block.accept(maker);
            condition.accept(maker);
            b.ifeq(b.startLabel());
        });
        return this;
    }

    @Override
    public CodeMaker load(Variable variable) {
        variable.load(this);

        return this;
    }

    @Override
    public CodeMaker load(ConstantDesc value) {
        cb.loadConstant(value);

        return this;
    }

    @Override
    public Variable new_(TypeModel type, Variable... arguments) {
        var v = new JVMInlinedVariableBuilder();
        v.addCode(cm -> {
            var cb = cm.codeBuilder();
            cb.new_(type.classDesc());
            cb.dup();
            for (var arg : arguments) {
                arg.load(cm);
            }

            cb.invokespecial(type.classDesc(), "<init>", type.model().getMethod("<init>", Arrays.stream(arguments).map(Variable::getType).toList()).methodTypeDesc());
        });
        v.setType(type);
        v.setCodeMaker(this);

        return v.build();
    }

    @Override
    public Variable superInit(Variable... arguments) {
        var v = new JVMInlinedVariableBuilder();
        var superType = method.owner().superClass().typeModel();
        v.addCode(cm -> {
            cm.this_().load(cm);
            cm.codeBuilder().invokespecial(superType.classDesc(), "<init>", superType.model().getMethod("<init>", Arrays.stream(arguments).map(Variable::getType).toList()).methodTypeDesc());
        });
        v.setType(superType);
        v.setCodeMaker(this);

        return v.build();
    }

    @Override
    public Variable newArray(TypeModel ofType, int size) {
        var v = var(ofType.asArray(1));
        v.addCode(cm -> {
            var cb = cm.codeBuilder();
            cb.loadConstant(size);
            Instruction inst;
            if (ofType.isPrimitive())
                inst = NewPrimitiveArrayInstruction.of(ofType.typeKind());
            else
                inst = NewReferenceArrayInstruction.of(cb.constantPool().classEntry(ofType.classDesc()));
            cb.with(inst);
        });
        v.setType(ofType.asArray(1));
        v.setCodeMaker(this);

        return v.build();
    }

    @Override
    public boolean hasVariable(String name) {
        return locals.containsKey(name);
    }

    private Variable allocateLocal(String name, TypeModel type, Object initialValue) {
        if (locals.containsKey(name))
            return locals.get(name);

        TypeModel varType = (type != null) ? type : initialValue instanceof Variable v ? v.getType() : TypeModel.of(initialValue);
        int slot = cb.allocateLocal(varType.typeKind());
        Variable var = new JVMVariable(slot, varType, this);
        locals.put(name, var);

        if (initialValue instanceof ConstantDesc constant) {
            var.set(constant);
        } else if (initialValue instanceof Variable variable) {
            var.set(variable);
        }

        return var;
    }

    @Override
    public Variable var(String name, TypeModel type, ConstantDesc value) {
        return allocateLocal(name, type, value);
    }

    @Override
    public Variable var(String name, TypeModel type, Variable value) {
        return allocateLocal(name, type, value);
    }

    @Override
    public InlinedVariableBuilder var(TypeModel type, Variable value) {
        if (type == null) {
            if (value != null)
                type = value.getType();
            else
                type = TypeModel.of(Object.class);
        }

        return new JVMInlinedVariableBuilder().setType(type).setCodeMaker(this).addCode(cm -> {
            if (value != null) {
                cm.load(value);
            }
        });
    }

    @Override
    public InlinedVariable var(TypeModel type, ConstantDesc value) {
        var v = new JVMInlinedVariable(value);
        if (type == null) {
            v.type = TypeModel.of(value);
        } else {
            v.type = type;
        }
        v.setCodeMaker(this);

        return v;
    }

    private Variable invokeStatic(MethodModel method, Object... arguments) {
        var v = var();
        v.addCode(cm -> {
            for (Object arg : arguments) {
                if (arg instanceof Variable variable) {
                    variable.load(cm);
                } else if (arg instanceof ConstantDesc constant) {
                    cm.load(constant);
                }
            }
            cm.codeBuilder().invokestatic(method.owner().classDesc(), method.name(), method.methodTypeDesc());
        });
        v.setType(method.returnType());
        return v.build();
    }

    @Override
    public Variable invokestatic(MethodModel method) {
        return invokeStatic(method);
    }

    @Override
    public Variable invokestatic(MethodModel method, Variable... arguments) {
        return invokeStatic(method, (Object[]) arguments);
    }

    @Override
    public Variable invokestatic(MethodModel method, ConstantDesc... arguments) {
        return invokeStatic(method, (Object[]) arguments);
    }

    @Override
    public Variable invokestatic(String methodName) {
        MethodModel methodModel = method.owner().getMethod(methodName);
        return invokeStatic(methodModel);
    }

    @Override
    public Variable invokestatic(String methodName, Variable... arguments) {
        MethodModel methodModel = method.owner().getMethod(methodName, Arrays.stream(arguments).map(Variable::getType).toList());
        return invokeStatic(methodModel, (Object[]) arguments);
    }

    @Override
    public Variable invokestatic(String methodName, ConstantDesc... arguments) {
        List<TypeModel> argumentTypes = Arrays.stream(arguments).map(c -> TypeModel.of(c.getClass())).toList();
        MethodModel methodModel = method.owner().getMethod(methodName, argumentTypes);
        return invokeStatic(methodModel, (Object[]) arguments);
    }
}