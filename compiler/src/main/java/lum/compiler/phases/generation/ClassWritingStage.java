package lum.compiler.phases.generation;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.phases.CompilerStage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

public class ClassWritingStage implements CompilerStage<CompilationInfo, GeneratedClassesResult, ClassWritingResult> {
    @Override
    public ClassWritingResult execute(CompilationInfo context, GeneratedClassesResult result) throws CompilationException {
        Path outDir = context.outputDirectory().normalize();
        outDir.toFile().mkdirs();
        Exception error = null;

        for (Map.Entry<Path, byte[]> entry : result.intermediateResult().entrySet()) {
            Path path = outDir.resolve(entry.getKey());
            byte[] bytes = entry.getValue();
            try {
                Objects.requireNonNullElse(path.getParent(), Path.of("")).toFile().mkdirs();
                Files.write(path, bytes);
            } catch (IOException e) {
                error = e;
                break;
            }
        }

        return new ClassWritingResult(error, error == null);
    }
}
