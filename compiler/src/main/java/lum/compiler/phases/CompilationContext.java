package lum.compiler.phases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class CompilationContext {
    private static final Logger logger = LoggerFactory.getLogger(CompilationContext.class);

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
            logger.error("Errors: ");
            for (var err : errors()) {
                logger.error(err.getMessage(), err);
            }
        }
    }

    public void printInfo() {
        if (!info.isEmpty()) {
            logger.info("Info: ");
            for (var info : info) {
                logger.info(info);
            }
        }
    }

    public void printWarnings() {
        if (!warnings().isEmpty()) {
            logger.warn("Warnings: ");
            for (var warn : warnings) {
                logger.warn(warn);
            }
        }
    }
}