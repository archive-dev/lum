package lum.compiler.phases.generation;

import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationInfo;
import lum.compiler.phases.CompilerStage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class ClassWritingStage implements CompilerStage<CompilationInfo, GeneratedClassesResult, ClassWritingResult> {
    @Override
    public ClassWritingResult execute(CompilationInfo context, GeneratedClassesResult result) throws CompilationException {
        CompilationException error = null;

        for (Map.Entry<Path, byte[]> entry : result.intermediateResult().entrySet()) {
            Path path = entry.getKey();
            byte[] bytes = entry.getValue();
            try {
                Files.write(path, bytes, StandardOpenOption.CREATE);
            } catch (IOException e) {
                error = new CompilationException(e.getMessage());
                break;
            }
        }

        return new ClassWritingResult(error, error == null);
    }
}
