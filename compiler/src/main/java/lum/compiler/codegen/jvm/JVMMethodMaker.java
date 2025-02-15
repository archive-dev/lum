package lum.compiler.codegen.jvm;

import lum.compiler.codegen.*;
import lum.core.model.ClassModel;
import lum.core.model.MethodModel;

import java.lang.classfile.MethodBuilder;
import java.lang.reflect.AccessFlag;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class JVMMethodMaker implements MethodMaker {
    final MethodModel model;
    private final List<Consumer<MethodBuilder>> builder = new ArrayList<>();

    public JVMMethodMaker(MethodModel model) {
        this.model = model;
    }

    @Override
    public MethodMaker withCode(Consumer<CodeMaker> code) {
        builder.add(mb -> {
            mb.withCode(c -> c.block(b -> code.accept(new JVMCodeMaker(b, model))));
        });
        return this;
    }

    @Override
    public Accessible access(AccessFlag flag) {
        builder.add(mb -> {
            mb.withFlags(flag);
        });
        return this;
    }

    @Override
    public Accessible access(AccessFlag... flags) {
        builder.add(mb -> {
            mb.withFlags(flags);
        });
        return this;
    }

    // todo:
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

    List<Consumer<MethodBuilder>> finish() {
        return builder;
    }
}
