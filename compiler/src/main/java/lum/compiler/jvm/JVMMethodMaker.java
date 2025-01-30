package lum.compiler.jvm;

import lum.compiler.bytecode.*;
import lum.core.model.ClassModel;
import lum.core.model.MethodModel;

import java.lang.classfile.MethodBuilder;
import java.lang.reflect.AccessFlag;
import java.util.function.Consumer;

public class JVMMethodMaker extends JVMCodeMaker implements MethodMaker {
    private final MethodModel method;

    private MethodBuilder mb;

    public JVMMethodMaker(MethodModel method) {
        super(null);
        this.method = method;
    }

    @Override
    public AnnotationMaker annotateWith(ClassModel annotation) {
        return null;
    }

    @Override
    public AnnotationMaker annotateWith(ClassMaker annotation) {
        return null;
    }

    @Override
    public AnnotationMaker annotateWith(Class<?> annotation) {
        return null;
    }

    @Override
    public Accessible access(AccessFlag flag) {
        return null;
    }

    @Override
    public Accessible access(AccessFlag... flags) {
        return null;
    }

    @Override
    public MethodMaker withCode(Consumer<CodeMaker> code) {
        return null;
    }
}
