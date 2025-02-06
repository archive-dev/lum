package lum.compiler.phases;

/// @param <T> Compilation context, the first parameter in execute() method.
/// @param <U> Result of the previous stage.
/// @param <R> Return type
public interface CompilerStage<T extends CompilationContext, U extends CompilationResult<?>, R extends CompilationResult<?>> {
    R execute(T context, U result) throws CompilationException;
}