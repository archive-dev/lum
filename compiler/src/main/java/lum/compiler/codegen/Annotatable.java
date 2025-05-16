package lum.compiler.codegen;

import lum.core.model.AnnotationModel;
import lum.core.model.ClassModel;

import java.lang.annotation.Annotation;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface Annotatable {
    AnnotationMaker annotateWith(ClassModel annotation);
    AnnotationMaker annotateWith(ClassMaker annotation);
    AnnotationMaker annotateWith(AnnotationModel annotation);
    AnnotationMaker annotateWith(Class<? extends Annotation> annotation);
}
