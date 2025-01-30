package lum.compiler.jvm;

import lum.compiler.bytecode.Annotatable;
import lum.compiler.bytecode.AnnotationMaker;
import lum.compiler.bytecode.ClassMaker;
import lum.core.model.ClassModel;

import java.lang.constant.Constable;

public class JVMAnnotationMaker implements AnnotationMaker {
    @Override
    public Annotatable setProperty(String name, Constable value) {
        return null;
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
}
