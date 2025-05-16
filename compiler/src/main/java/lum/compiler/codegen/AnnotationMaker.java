package lum.compiler.codegen;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface AnnotationMaker {
    AnnotationMaker setProperty(String name, Object value);

    AnnotationMaker setRuntimeVisibility(boolean visibility);
}
