package lum.compiler;

import lum.compiler.phases.CompilationInfo;
import lum.compiler.pipeline.Executor;

import java.nio.file.Path;
import java.util.ArrayList;

public class Compiler {
    public static void main(String[] args) {
        new Executor().execute(new CompilationInfo(Path.of(args[0]), new ArrayList<>(), new ArrayList<>())).print();
    }
}
