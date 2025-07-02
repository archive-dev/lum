package lum.compiler.phases.generation;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.phases.CompilerStage;
import lum.compiler.phases.Target;
import lum.compiler.phases.parsing.ParsedClassesResult;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CodeGenerationStage implements CompilerStage<CompilationInfo, ParsedClassesResult, GeneratedClassesResult> {
    private static final Map<Target, Supplier<ClassGenerator>> classGenerators = new HashMap<>();

    private final Target compilationTarget;

    public CodeGenerationStage(Target compilationTarget) {
        this.compilationTarget = compilationTarget;
    }

    @Override
    public GeneratedClassesResult execute(CompilationInfo context, ParsedClassesResult result) throws CompilationException {
        var classModels = result.intermediateResult();

        HashMap<Path, byte[]> files = new HashMap<>();

        Exception error = null;

        for (var model : classModels) {
            try {
                Path path = context.outputDirectory().orElse(Path.of("out")).resolve(model.pkg().replace(".", "/"));
                byte[] file = classGenerators.get(compilationTarget).get().generate(model);
                files.put(path, file);
            } catch (Exception e) {
                error = e;
                break;
            }
        }
        return new GeneratedClassesResult(files, error, error == null);
    }
}
