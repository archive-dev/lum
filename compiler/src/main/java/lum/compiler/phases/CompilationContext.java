package lum.compiler.phases;

import java.util.List;

public abstract class CompilationContext {
    private final List<String> warnings;
    private final List<Exception> errors;

    public CompilationContext(List<String> warnings, List<Exception> errors) {
        this.warnings = warnings;
        this.errors = errors;
    }

    public final List<String> warnings() {
        return warnings;
    }

    public List<Exception> errors() {
        return errors;
    }

    public void print() {
        if (!errors().isEmpty()) {
            System.out.println("Errors: ");
            for (var err : errors()) {
                err.printStackTrace(System.out);
            }
        }

        if (!warnings().isEmpty()) {
            System.out.println("Warnings: ");
            for (var warn : warnings) {
                System.out.println(warn);
            }
        }
    }
}