package lum.compiler.codegen.jvm;

import lum.compiler.codegen.CodeMaker;
import lum.compiler.codegen.InlinedVariable;
import lum.compiler.codegen.InlinedVariableBuilder;
import lum.compiler.codegen.Variable;
import lum.core.model.MethodModel;
import lum.core.model.ParameterModel;
import lum.core.model.TypeModel;

import java.lang.classfile.CodeBuilder;
import java.lang.constant.ConstantDesc;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class JVMCodeMaker implements CodeMaker {
    private static final ConstantDesc[] EMPTY_ARGS = new ConstantDesc[0];

    private final HashMap<String, Variable> locals = new HashMap<>();
    private final HashMap<Integer, Variable> parameters = new HashMap<>();

    private final CodeBuilder.BlockCodeBuilder cb;
    private final MethodModel method;

    public JVMCodeMaker(CodeBuilder.BlockCodeBuilder cb, MethodModel method) {
        this.cb = cb;
        this.method = method;

        ParameterModel[] methodParameters = method.parameters();
        for (int i = 0; i < methodParameters.length; i++) {
            var param = methodParameters[i];
            parameters.put(i, new JVMVariable(i, param.type(), this));
        }
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
        if (method.isStatic()) return null;
        return var("this");
    }

    @Override
    public Variable parameter(int index) {
        return parameters.get(index);
    }

    @Override
    public CodeMaker ifThen(Variable value, final Consumer<CodeMaker> ifHandler) {
        value.load();
        cb.ifThen((b) -> ifHandler.accept(new JVMCodeMaker(b, this)));
        return this;
    }

    @Override
    public CodeMaker ifThenElse(Variable value, Consumer<CodeMaker> ifHandler, Consumer<CodeMaker> elseHandler) {
        value.load();
        cb.ifThenElse(
                (b) -> ifHandler.accept(new JVMCodeMaker(b, this)),
                (b) -> elseHandler.accept(new JVMCodeMaker(b, this))
        );
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
        cb.return_(value.getType().typeKind());
        return this;
    }

    @Override
    public CodeMaker return_(ConstantDesc value) {
        cb.ldc(value);
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
    public CodeMaker for_(Variable i, Consumer<CodeMaker> condition, BiConsumer<CodeMaker, Variable> update, Consumer<CodeMaker> block) {
        i = var("i", i);

        Variable finalI = i;
        cb.block((b) -> {
            var maker = new JVMCodeMaker(b, this);
            condition.accept(maker);
            b.ifeq(b.breakLabel());
            block.accept(maker);
            update.accept(maker, finalI);
            b.goto_(b.startLabel());
        });

        return this;
    }

    @Override
    public CodeMaker foreach(Variable i, Variable iterator, Consumer<CodeMaker> block) {
        if (!iterator.getType().isArray()) {
            Variable iterVar = var("iter", iterator);

            cb.block((b) -> {
                var maker = new JVMCodeMaker(b, this);

                // Check if iterator has next element
                iterVar.invoke("hasNext", EMPTY_ARGS)
                        .load();

                // If no more elements, break the loop
                b.ifeq(b.breakLabel());

                // Get the next element and assign to loop variable
                i.set(iterVar.invoke("next", EMPTY_ARGS));

                // Execute the loop block
                block.accept(maker);

                // Continue to next iteration
                b.goto_(b.startLabel());
            });
        } else {
            // Inside the else block of foreach method where iterator is an array
            Variable length = var("len", TypeModel.of(int.class));
            length.set(iterator.arrayLength());

            Variable index = var("i", TypeModel.of(int.class));
            index.set(0);

            for_(index,
                    _ -> {
                        // condition: i < array.length
                        load(index.lt(length));
                    },
                    (_, idx) -> {
                        // update: i++
                        idx.increment(var(1));
                    },
                    maker -> {
                        // block: assign array[i] to loop variable and execute block
                        i.set(iterator.arrayAccess(new Variable[]{index}));
                        block.accept(maker);
                    }
            );
        }

        return this;
    }

    @Override
    public CodeMaker while_(Consumer<CodeMaker> condition, Consumer<CodeMaker> block) {
        cb.block(b -> {
            var maker = new JVMCodeMaker(b, this);
            condition.accept(maker);
            b.ifne(b.breakLabel());
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
        variable.load();

        return this;
    }

    @Override
    public CodeMaker load(ConstantDesc value) {
        cb.ldc(value);

        return this;
    }

    @Override
    public Variable var(String name, TypeModel type, ConstantDesc value) {
        if (locals.containsKey(name))
            return locals.get(name);
        if (type == null) {
            if (value == null) {
                type = TypeModel.of(Object.class);
            } else {
                type = TypeModel.of(value.getClass());
            }
        }

        int slot = cb.allocateLocal(type.typeKind());
        Variable var = new JVMVariable(slot, type, this);
        locals.put(name, var);
        if (value != null)
            var.set(value);
        return var;
    }

    @Override
    public Variable var(String name, TypeModel type, Variable value) {
        if (locals.containsKey(name))
            return locals.get(name);
        if (type == null) {
            if (value == null) {
                type = TypeModel.of(Object.class);
            } else {
                type = value.getType();
            }
        }

        int slot = cb.allocateLocal(type.typeKind());
        Variable var = new JVMVariable(slot, type, this);
        locals.put(name, var);
        if (value != null)
            var.set(value);
        return var;
    }

    @Override
    public InlinedVariableBuilder var(TypeModel type, Variable value) {
        if (type == null) {
            if (value != null)
                type = value.getType();
            else
                type = TypeModel.of(Object.class);
        }

        return new JVMInlinedVariableBuilder().var("inlined", type, value);
    }

    @Override
    public InlinedVariable var(TypeModel type, ConstantDesc value) {
        return new JVMInlinedVariable(value);
    }

    @Override
    public Variable invokestatic(MethodModel method) {
        var v = var();
        v.addCode(codeMaker -> ((JVMCodeMaker) codeMaker).cb.invokestatic(method.owner().classDesc(), method.name(), method.methodTypeDesc()));
        return v.build();
    }

    @Override
    public Variable invokestatic(MethodModel method, Variable... arguments) {
        for (var arg :
                arguments) {
            arg.load();
        }

        return invokestatic(method);
    }

    @Override
    public Variable invokestatic(MethodModel method, ConstantDesc... arguments) {
        for (var arg :
                arguments) {
            cb.ldc(arg);
        }

        return invokestatic(method);
    }
}
