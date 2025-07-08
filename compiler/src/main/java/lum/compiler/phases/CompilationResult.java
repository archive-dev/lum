package lum.compiler.phases;

public abstract class CompilationResult<R> {
    private final Exception error;
    private final boolean successful;
    protected final R intermediateResult;

    public CompilationResult(R intermediateResult, Exception error, boolean successful) {
        this.error = error;
        this.successful = successful;
        this.intermediateResult = intermediateResult;
    }

    public R intermediateResult() {
        return this.intermediateResult;
    }

    public final Exception error() {
        return error;
    }

    public final boolean successful() {
        return successful;
    }
}
