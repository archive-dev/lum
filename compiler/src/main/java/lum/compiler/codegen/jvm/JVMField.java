package lum.compiler.codegen.jvm;

import lum.compiler.codegen.CodeMaker;
import lum.compiler.codegen.Field;
import lum.compiler.codegen.Variable;
import lum.core.model.ClassModel;
import lum.core.model.FieldModel;
import lum.core.model.TypeModel;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.Opcode;
import java.lang.constant.ConstantDesc;
import java.util.function.Consumer;

class JVMField extends JVMVariable implements Field {
    private final Variable accessor;
    private final ClassModel owner;
    private final FieldModel field;

    public JVMField(CodeMaker codeMaker, Variable accessor, ClassModel owner, FieldModel field) {
        super(-1, codeMaker);
        this.accessor = accessor;
        this.owner = owner;
        this.field = field;
        this.type = field.type();
    }

    /// Assumes that stack has value to put in field via PUTSTATIC or PUTFIELD
    @Override
    public void store() {
        CodeBuilder cb = codeMaker().codeBuilder();
        if (field.isStatic())
            cb.putstatic(owner.classDesc(), field.name(), field.type().classDesc());
        else {
            accessor.load(codeMaker());
            cb.swap();
            cb.putfield(owner.classDesc(), field.name(), field.type().classDesc());
        }
    }

    @Override
    public void load(CodeMaker to) {
        CodeBuilder cb = to.codeBuilder();
        if (field.isStatic())
            cb.getstatic(owner.classDesc(), field.name(), field.type().classDesc());
        else {
            accessor.load(codeMaker());
            cb.getfield(owner.classDesc(), field.name(), field.type().classDesc());
        }
    }

    @Override
    public Variable set(TypeModel newType, ConstantDesc value) {
        if (!field.isStatic())
            accessor.load(codeMaker());
        codeMaker().load(value);
        Opcode opcode = field.isStatic() ? Opcode.PUTSTATIC : Opcode.PUTFIELD;

        codeMaker().codeBuilder().fieldAccess(opcode, owner.classDesc(), field.name(), field.type().classDesc());

        return this;
    }

    @Override
    public Variable set(TypeModel newType, Variable value) {
        if (!field.isStatic())
            accessor.load(codeMaker());
        codeMaker().load(value);
        Opcode opcode = field.isStatic() ? Opcode.PUTSTATIC : Opcode.PUTFIELD;

        codeMaker().codeBuilder().fieldAccess(opcode, owner.classDesc(), field.name(), field.type().classDesc());

        return this;
    }

    @Override
    public Variable set(TypeModel newType, Consumer<CodeMaker> expression) {
        if (!field.isStatic())
            accessor.load(codeMaker());
        expression.accept(codeMaker());
        Opcode opcode = field.isStatic() ? Opcode.PUTSTATIC : Opcode.PUTFIELD;

        codeMaker().codeBuilder().fieldAccess(opcode, owner.classDesc(), field.name(), field.type().classDesc());

        return this;
    }


}
