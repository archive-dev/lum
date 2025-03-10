package lum.compiler.codegen;

import lum.core.model.MethodModel;
import lum.core.model.TypeModel;

import java.lang.classfile.CodeBuilder;
import java.lang.constant.ConstantDesc;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public interface CodeMaker {
    /// Returns the underlying CodeBuilder.
    ///
    /// @return The CodeBuilder instance.
    CodeBuilder codeBuilder();

    /// Represents the `this` keyword.
    ///
    /// @return A Variable representing `this`.
    Variable this_();

    /// Represents the `super` keyword.
    ///
    /// @return A Variable representing `super`.
    Variable super_();

    /// Represents a method parameter at the given index.
    ///
    /// @param index The index of the parameter.
    /// @return A Variable representing the parameter.
    Variable parameter(int index);

    /// Executes the given code block if the provided value is true.
    ///
    /// @param value     The condition to check.
    /// @param ifHandler The code block to execute if the condition is true.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker ifThen(Variable value, Consumer<CodeMaker> ifHandler);

    /// Executes one of the two given code blocks based on the provided value.
    ///
    /// @param value       The condition to check.
    /// @param ifHandler   The code block to execute if the condition is true.
    /// @param elseHandler The code block to execute if the condition is false.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker ifThenElse(Variable value, Consumer<CodeMaker> ifHandler, Consumer<CodeMaker> elseHandler);

    /// Executes a code block based on the value of a variable.
    ///
    /// @param value The variable to switch on.
    /// @param cases A map of values to code blocks.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker switch_(Variable value, Map<Function<CodeMaker, Object>, Consumer<CodeMaker>> cases);

    /// Returns from the current method.
    ///
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker return_();

    /// Returns the given variable from the current method.
    ///
    /// @param value The variable to return.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker return_(Variable value);

    /// Returns the given constant from the current method.
    ///
    /// @param value The constant to return.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker return_(ConstantDesc value);

    /// Breaks out of the current loop.
    ///
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker break_();

    /// Continues to the next iteration of the current loop.
    ///
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker continue_();

    /// Executes a for loop.
    ///
    /// @param i         The loop variable.
    /// @param condition The loop condition.
    /// @param update    The loop update.
    /// @param block     The loop body.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker for_(Variable i, BiConsumer<CodeMaker, Variable> condition, BiConsumer<CodeMaker, Variable> update, BiConsumer<CodeMaker, Variable> block);

    /// Iterates over the elements of an iterable or array, executing a provided block of code for each element.
    ///
    /// If the iterable is an instance of [Iterable], the method gets an iterator and uses it to traverse
    /// the elements. For each element, the provided block is executed with a [CodeMaker] and a [Variable]
    /// representing the current element.
    ///
    ///
    /// If the iterable is an array, a traditional `for` loop is used to iterate over the elements. The block is
    /// executed for each element with a [CodeMaker] and a [Variable] representing the current element.
    ///
    ///
    /// @param iterable The [Variable] representing the iterable or array to iterate over.
    ///                 Must be of a type that implements [Iterable] or is an array.
    /// @param block    A [BiConsumer] that accepts a [CodeMaker] and a [Variable] representing
    ///                 the current element in the iteration. This block contains the code to be executed for each
    ///                 element.
    /// @return The [CodeMaker] instance, allowing for method chaining.
    /// @throws IllegalStateException if the provided `iterable` is neither an [Iterable] nor an array.
    CodeMaker foreach(Variable iterable, BiConsumer<CodeMaker, Variable> block);

    /// Iterates over the elements of an iterable or array, executing a provided block of code for each element.
    ///
    /// @param i        The loop variable.
    /// @param iterable The iterable to loop over.
    /// @param block    The code to execute for each element.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker foreach(Variable i, Variable iterable, BiConsumer<CodeMaker, Variable> block);

    /// Executes a while loop.
    ///
    /// @param condition The loop condition.
    /// @param block     The loop body.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker while_(Consumer<CodeMaker> condition, Consumer<CodeMaker> block);

    /// Executes a do-while loop.
    ///
    /// @param condition The loop condition.
    /// @param block     The loop body.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker doWhile(Consumer<CodeMaker> condition, Consumer<CodeMaker> block);

    /// Loads a variable onto the stack.
    ///
    /// @param variable The variable to load.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker load(Variable variable);

    /// Loads a constant onto the stack.
    ///
    /// @param value The constant to load.
    /// @return The CodeMaker instance, allowing for method chaining.
    CodeMaker load(ConstantDesc value);

    /// Creates a new object of the given type.
    ///
    /// @param type      The type of the object to create.
    /// @param arguments The constructor arguments.
    /// @return A Variable representing the new object.
    Variable new_(TypeModel type, Variable... arguments);

    /// Calls the super constructor.
    ///
    /// @param arguments The constructor arguments.
    /// @return A Variable representing the result of the super constructor call.
    Variable superInit(Variable... arguments);

    /// Creates a new array of the given type and size.
    ///
    /// @param ofType The type of the array elements.
    /// @param size   The size of the array.
    /// @return A Variable representing the new array.
    Variable newArray(TypeModel ofType, int size);

    /// Checks if a variable with the given name exists in the current scope.
    ///
    /// @param name The name of the variable to check.
    /// @return True if a variable with the given name exists, false otherwise.
    boolean hasVariable(String name);

    /// Creates a new variable with the given name, type, and initial value.
    ///
    /// @param name  The name of the variable.
    /// @param type  The type of the variable.
    /// @param value The initial value of the variable.
    /// @return A Variable representing the new variable.
    Variable var(String name, TypeModel type, ConstantDesc value);

    /// Creates a new variable with the given name, type, and initial value.
    ///
    /// @param name  The name of the variable.
    /// @param type  The type of the variable.
    /// @param value The initial value of the variable.
    /// @return A Variable representing the new variable.
    Variable var(String name, TypeModel type, Variable value);

    /// Creates a new variable with the given name and type.
    ///
    /// @param name The name of the variable.
    /// @param type The type of the variable.
    /// @return A Variable representing the new variable.
    default Variable var(String name, TypeModel type) {
        return var(name, type, (Variable) null);
    }

    /// Creates a new variable with the given name and initial value.
    ///
    /// @param name  The name of the variable.
    /// @param value The initial value of the variable.
    /// @return A Variable representing the new variable.
    default Variable var(String name, Variable value) {
        return var(name, null, value);
    }

    /// Creates a new variable with the given name and initial value.
    ///
    /// @param name  The name of the variable.
    /// @param value The initial value of the variable.
    /// @return A Variable representing the new variable.
    default Variable var(String name, ConstantDesc value) {
        return var(name, null, value);
    }

    /// Creates a new variable with the given name.
    ///
    /// @param name The name of the variable.
    /// @return A Variable representing the new variable.
    default Variable var(String name) {
        return var(name, null, (ConstantDesc) null);
    }

    /// Creates a new inlined variable builder with the given type and initial value.
    ///
    /// @param type  The type of the variable.
    /// @param value The initial value of the variable.
    /// @return An InlinedVariableBuilder for creating the variable.
    InlinedVariableBuilder var(TypeModel type, Variable value);

    /// Creates a new inlined variable builder with the given type.
    ///
    /// @param type The type of the variable.
    /// @return An InlinedVariableBuilder for creating the variable.
    default InlinedVariableBuilder var(TypeModel type) {
        return var(type, (Variable) null);
    }

    /// Creates a new inlined variable builder with the given initial value.
    ///
    /// @param value The initial value of the variable.
    /// @return An InlinedVariableBuilder for creating the variable.
    default InlinedVariableBuilder var(Variable value) {
        return var(value.getType(), value);
    }

    /// Creates a new inlined variable builder.
    ///
    /// @return An InlinedVariableBuilder for creating the variable.
    default InlinedVariableBuilder var() {
        return var((TypeModel) null, (Variable) null);
    }

    /// Creates a new inlined variable with the given type and initial value.
    ///
    /// @param type  The type of the variable.
    /// @param value The initial value of the variable.
    /// @return An InlinedVariable representing the new variable.
    InlinedVariable var(TypeModel type, ConstantDesc value);

    /// Creates a new inlined variable with the given initial value.
    ///
    /// @param value The initial value of the variable.
    /// @return An InlinedVariable representing the new variable.
    default InlinedVariable var(ConstantDesc value) {
        return var(TypeModel.of(value), value);
    }

    /// Invokes a static method.
    ///
    /// @param method The method to invoke.
    /// @return A Variable representing the result of the method call.
    Variable invokestatic(MethodModel method);

    /// Invokes a static method.
    ///
    /// @param methodName The name of the method to invoke.
    /// @return A Variable representing the result of the method call.
    Variable invokestatic(String methodName);

    /// Invokes a static method with the given arguments.
    ///
    /// @param method    The method to invoke.
    /// @param arguments The method arguments.
    /// @return A Variable representing the result of the method call.
    Variable invokestatic(MethodModel method, Variable... arguments);

    /// Invokes a static method with the given arguments.
    ///
    /// @param methodName The name of the method to invoke.
    /// @param arguments  The method arguments.
    /// @return A Variable representing the result of the method call.
    Variable invokestatic(String methodName, Variable... arguments);

    /// Invokes a static method with the given arguments.
    ///
    /// @param method    The method to invoke.
    /// @param arguments The method arguments.
    /// @return A Variable representing the result of the method call.
    Variable invokestatic(MethodModel method, ConstantDesc... arguments);

    /// Invokes a static method with the given arguments.
    ///
    /// @param methodName The name of the method to invoke.
    /// @param arguments  The method arguments.
    /// @return A Variable representing the result of the method call.
    Variable invokestatic(String methodName, ConstantDesc... arguments);
}
