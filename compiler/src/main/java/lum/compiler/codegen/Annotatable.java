package lum.compiler.codegen;

import lum.core.model.ClassModel;

import java.lang.annotation.Annotation;

public interface Annotatable {
    AnnotationMaker annotateWith(ClassModel annotation);
    AnnotationMaker annotateWith(ClassMaker annotation);
    AnnotationMaker annotateWith(Class<? extends Annotation> annotation);
}
