package lum.compiler.phases;

public abstract class CompilationResult<R> {
    private final CompilationException error;
    private final boolean successful;

    public CompilationResult(CompilationException error, boolean successful) {
        this.error = error;
        this.successful = successful;
    }

    public abstract R intermediateResult();

    public final CompilationException error() {
        return error;
    }

    public final boolean successful() {
        return successful;
    }
}
