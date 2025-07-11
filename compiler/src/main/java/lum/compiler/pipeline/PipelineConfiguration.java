package lum.compiler.pipeline;

import lum.compiler.phases.Target;
import lum.compiler.phases.generation.ClassWritingStage;
import lum.compiler.phases.generation.CodeGenerationStage;
import lum.compiler.phases.CompilerStage;
import lum.compiler.phases.parsing.ParsingStage;

import java.util.List;

public class PipelineConfiguration {
    public static List<CompilerStage<?, ?, ?>> getDefaultStages() {
        return List.of(
                new ParsingStage(),
                new CodeGenerationStage(Target.JVM),
                new ClassWritingStage()
        );
    }
}
