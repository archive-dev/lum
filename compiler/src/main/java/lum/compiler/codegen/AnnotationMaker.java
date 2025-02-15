package lum.compiler.codegen;

import java.lang.constant.Constable;

public interface AnnotationMaker extends Annotatable {
    Annotatable setProperty(String name, Constable value);
}
