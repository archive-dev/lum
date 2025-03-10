package lum.compiler;

import lum.compiler.phases.CompilationContext;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.pipeline.Executor;

import java.nio.file.Path;
import java.util.ArrayList;

public class Compiler {
    private final CompilationContext compilationContext;

    public Compiler(CompilationContext compilationContext) {
        this.compilationContext = compilationContext;
    }

    public int compile(Path path) {
        var res = new Executor().execute(new CompilationInfo(path, new ArrayList<>(), new ArrayList<>()));
        if (!res.errors().isEmpty()) {
            res.print();
            return 1;
        }
        if (!res.warnings().isEmpty())
            res.print();

        return 0;
    }

    public static void main(String[] args) {
        new Executor().execute(new CompilationInfo(Path.of(args[0]), new ArrayList<>(), new ArrayList<>())).print();
    }
}
