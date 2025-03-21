package lum.compiler.phases;

import java.util.List;

public abstract class CompilationContext {
    private final List<String> info;
    private final List<String> warnings;
    private final List<Exception> errors;

    public CompilationContext(List<String> warnings, List<Exception> errors) {
        this.warnings = warnings;
        this.errors = errors;
        info = List.of();
    }

    public CompilationContext(List<String> info, List<String> warnings, List<Exception> errors) {
        this.info = info;
        this.warnings = warnings;
        this.errors = errors;
    }

    public List<String> info() {
        return info;
    }

    public final List<String> warnings() {
        return warnings;
    }

    public List<Exception> errors() {
        return errors;
    }

    public void print() {
        printInfo();
        printWarnings();
        printErrors();
    }

    public void printErrors() {
        if (!errors().isEmpty()) {
            System.out.println("Errors: ");
            for (var err : errors()) {
                err.printStackTrace(System.out);
            }
        }
    }

    public void printInfo() {
        if (!info.isEmpty()) {
            System.out.println("Info: ");
            for (var info : info) {
                System.out.println(info);
            }
        }
    }

    public void printWarnings() {
        if (!warnings().isEmpty()) {
            System.out.println("Warnings: ");
            for (var warn : warnings) {
                System.out.println(warn);
            }
        }
    }
}