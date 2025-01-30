package lum.compiler.bytecode;

import java.lang.constant.Constable;

public interface AnnotationMaker extends Annotatable {
    Annotatable setProperty(String name, Constable value);
}
