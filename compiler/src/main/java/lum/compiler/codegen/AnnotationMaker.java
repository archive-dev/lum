package lum.compiler.codegen;

public interface AnnotationMaker {
    AnnotationMaker setProperty(String name, Object value);

    AnnotationMaker setRuntimeVisibility(boolean visibility);
}
