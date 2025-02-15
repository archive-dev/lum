package lum.compiler.codegen;

import lum.core.model.ClassModel;

public interface Annotatable {
    AnnotationMaker annotateWith(ClassModel annotation);
    AnnotationMaker annotateWith(ClassMaker annotation);
    AnnotationMaker annotateWith(Class<?> annotation);
}
