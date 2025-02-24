package lum.compiler.codegen.jvm;

import lum.compiler.codegen.CodeMaker;
import lum.compiler.codegen.Variable;
import lum.core.model.TypeModel;

import java.lang.constant.ConstantDesc;
import java.util.function.Consumer;

public class JVMArrayElement extends JVMVariable {
    private final Variable array;
    private final TypeModel elementType;

    private final Consumer<CodeMaker> indexHandler;

    public JVMArrayElement(Consumer<CodeMaker> indexHandler, Variable array) {
        super(-1, array.codeMaker());
        this.indexHandler = indexHandler;
        this.array = array;
        this.elementType = array.getType().asComponent();
        this.type = this.elementType;
    }

    public JVMArrayElement(int index, Variable array) {
        super(index, array.codeMaker());
        this.array = array;
        this.elementType = array.getType().asComponent();
        this.type = this.elementType;
        indexHandler = null;
    }

    @Override
    public void load(CodeMaker to) {
        array.load(to);
        if (getSlot() != -1)
            to.load(getSlot());
        else
            indexHandler.accept(to);
        to.codeBuilder().arrayLoad(elementType.typeKind());
    }

    @Override
    public void store() {
        var cb = array.codeMaker().codeBuilder();
        array.load(codeMaker());
        cb.swap();
        if (getSlot() != -1)
            array.codeMaker().load(getSlot());
        else
            indexHandler.accept(array.codeMaker());
        cb.swap();
        cb.arrayStore(elementType.typeKind());
    }

    @Override
    public Variable set(TypeModel newType, ConstantDesc value) {
        array.load(codeMaker());
        indexHandler.accept(codeMaker());
        codeMaker().load(value);
        codeMaker().codeBuilder().arrayStore(elementType.typeKind());

        return this;
    }

    @Override
    public Variable set(TypeModel newType, Variable value) {
        array.load(codeMaker());
        indexHandler.accept(codeMaker());
        codeMaker().load(value);
        codeMaker().codeBuilder().arrayStore(elementType.typeKind());

        return this;
    }

    @Override
    public Variable set(TypeModel newType, Consumer<CodeMaker> expression) {
        array.load(codeMaker());
        indexHandler.accept(codeMaker());
        expression.accept(codeMaker());
        codeMaker().codeBuilder().arrayStore(elementType.typeKind());

        return this;
    }
}
