package lum.compiler.phases;

/// @param <T> Compilation context, the first parameter in {@link #execute(T, U)} method.
/// @param <U> Result of the previous stage. Second parameter in {@link #execute(T, U)} method.
/// @param <R> Return type
public interface CompilerStage<T extends CompilationContext, U extends CompilationResult<?>, R extends CompilationResult<?>> {
    R execute(T context, U previousResult) throws CompilationException;
}