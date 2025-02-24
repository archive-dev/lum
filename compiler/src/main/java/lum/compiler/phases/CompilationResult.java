package lum.compiler.phases;

import java.util.Objects;

public abstract class CompilationResult<R> {
    private final Exception error;
    private final boolean successful;

    public CompilationResult(Exception error, boolean successful) {
        this.error = error;
        this.successful = successful;
    }

    public abstract R intermediateResult();

    public final Exception error() {
        return error;
    }

    public final boolean successful() {
        return successful;
    }
}
