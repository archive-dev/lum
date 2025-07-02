package lum.compiler.pipeline;

import lum.compiler.phases.CompilationContext;
import lum.compiler.phases.CompilationResult;
import lum.compiler.phases.CompilerStage;

import java.util.List;

public class Executor {

    private final List<CompilerStage<?, ?, ?>> stages;

    public Executor() {
        this.stages = PipelineConfiguration.getDefaultStages();
    }

    @SuppressWarnings("unused")
    public Executor(List<CompilerStage<?, ?, ?>> stages) {
        this.stages = stages;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CompilationContext execute(CompilationContext context) {
        CompilationResult<?> result = null;
        for (CompilerStage stage : stages) {
            try {
                result = stage.execute(context, result);
                if (!result.successful()) {
                    throw result.error();
                }
            } catch (Exception e) {
                context.errors().add(e);
            }
        }

        return context;
    }
}
