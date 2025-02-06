package lum.compiler.pipeline;

import lum.compiler.phases.CompilationContext;
import lum.compiler.phases.CompilationException;
import lum.compiler.phases.CompilationResult;
import lum.compiler.phases.CompilerStage;

import java.util.List;

public class Executor {

    private final List<CompilerStage> stages;

    public Executor() {
        this.stages = PipelineConfiguration.getDefaultStages();
    }

    public Executor(List<CompilerStage> stages) {
        this.stages = stages;
    }

    public CompilationResult<?> execute(CompilationContext context) {
        CompilationResult<?> result = null;
        for (var stage : stages) {
            try {
                result = stage.execute(context, result);
                if (!result.successful()) {
                    throw result.error();
                }
            } catch (CompilationException e) {
                context.errors().add(e);
            }
        }

        return result;
    }
}
