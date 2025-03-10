package lum.compiler.codegen;

import java.util.function.Consumer;

public interface MethodMaker extends Annotatable, Accessible {
    MethodMaker withCode(Consumer<CodeMaker> code);
}
