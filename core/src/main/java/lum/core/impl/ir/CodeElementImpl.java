package lum.core.impl.ir;

import lum.core.ir.CodeElement;
import lum.core.ir.Operator;
import lum.core.model.FieldModel;
import lum.core.model.Member;
import lum.core.model.MethodModel;
import lum.core.model.TypeModel;
import lum.lang.struct.Either;
import lum.lang.struct.Pair;
import org.jetbrains.annotations.NotNull;

import java.lang.constant.ConstantDesc;
import java.util.List;
import java.util.Optional;

public final class CodeElementImpl {

    // Basic CodeBlock implementation
    public record CodeBlockImpl(
            Optional<String> name,
            Optional<CodeElement.CodeBlock> parent,
            MethodModel ownerMethod,
            List<CodeElement> elements
    ) implements CodeElement.CodeBlock {}

    // Expression implementations
    public record ArrayExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            Optional<CodeElement.ExpressionElement[]> values
    ) implements CodeElement.ArrayExpression {}

    public record DictExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            CodeElement.ExpressionElement[] keys,
            CodeElement.ExpressionElement[] values
    ) implements CodeElement.DictExpression {}

    public record LambdaExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            Pair<String, TypeModel>[] parameters,
            CodeElement.CodeBlock block
    ) implements CodeElement.LambdaExpression {}

    public record IdentifierExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            Either<Either<String, TypeModel>, Member> identifier
    ) implements CodeElement.IdentifierExpression {}

    public record SuperAccessExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            Either<Either<String, TypeModel>, Member> identifier
    ) implements CodeElement.SuperAccessExpression {}

    // Literal expressions
    public record TrueExprImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type
    ) implements CodeElement.TrueExpr {}

    public record FalseExprImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type
    ) implements CodeElement.FalseExpr {}

    public record NullExprImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type
    ) implements CodeElement.NullExpr {}

    public record StringExprImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            String value
    ) implements CodeElement.StringExpr {}

    public record NumberExprImpl<T extends Number & ConstantDesc>(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            T value
    ) implements CodeElement.NumberExpr<T> {}

    // Complex expressions
    public record AssignmentExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            Either<Either<CodeElement.VariableDeclarationStatement, CodeElement.AssignmentExpression>, CodeElement.ArrayAccessExpression> variable,
            CodeElement.ExpressionElement value
    ) implements CodeElement.AssignmentExpression {}

    public record BinaryExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            CodeElement.ExpressionElement left,
            Operator.BinaryOperator operator,
            CodeElement.ExpressionElement right
    ) implements CodeElement.BinaryExpression {}

    public record UnaryPrefixExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            Operator.UnaryOperator operator,
            CodeElement.ExpressionElement expression
    ) implements CodeElement.UnaryPrefixExpression {}

    public record UnaryPostfixExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            CodeElement.ExpressionElement expression,
            Operator.UnaryOperator operator
    ) implements CodeElement.UnaryPostfixExpression {}

    public record CastExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            TypeModel castType,
            CodeElement.ExpressionElement expression
    ) implements CodeElement.CastExpression {}

    public record MemberAccessExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            Optional<CodeElement.ExpressionElement> expression,
            Member member
    ) implements CodeElement.MemberAccessExpression {}

    public record CallExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            Optional<CodeElement.ExpressionElement> caller,
            @NotNull MethodModel method,
            Optional<CodeElement.ExpressionElement[]> arguments
    ) implements CodeElement.CallExpression {}

    public record NewExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            @NotNull MethodModel method,
            Optional<CodeElement.ExpressionElement[]> arguments
    ) implements CodeElement.NewExpression {}

    public record ArrayAccessExpressionImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            CodeElement.ExpressionElement expression,
            CodeElement.ExpressionElement index
    ) implements CodeElement.ArrayAccessExpression {}

    // Statement implementations
    public record VariableDeclarationStatementImpl(
            Optional<CodeElement.CodeBlock> parent,
            TypeModel type,
            String name,
            Optional<CodeElement.ExpressionElement> value
    ) implements CodeElement.VariableDeclarationStatement {}

    public record IfStatementImpl(
            Optional<CodeElement.CodeBlock> parent,
            CodeElement.ExpressionElement ifCondition,
            CodeElement.CodeBlock ifBlock,
            Optional<Pair<CodeElement.ExpressionElement, CodeElement.CodeBlock>[]> elifConditions,
            Optional<CodeElement.CodeBlock> elseBlock
    ) implements CodeElement.IfStatement {}

    public record SwitchStatementImpl(
            Optional<CodeElement.CodeBlock> parent,
            CodeElement.ExpressionElement value,
            Pair<CodeElement.ExpressionElement, CodeElement.CodeBlock>[] cases,
            CodeElement.CodeBlock defaultCase
    ) implements CodeElement.SwitchStatement {}

    public record ReturnStatementImpl(
            Optional<CodeElement.CodeBlock> parent,
            Optional<CodeElement.ExpressionElement> returnValue
    ) implements CodeElement.ReturnStatement {}

    public record BreakStatementImpl(
            Optional<CodeElement.CodeBlock> parent,
            Optional<String> breakLabel
    ) implements CodeElement.BreakStatement {}

    public record ContinueStatementImpl(
            Optional<CodeElement.CodeBlock> parent,
            Optional<String> continueLabel
    ) implements CodeElement.ContinueStatement {}

    // Loop implementations
    public record DoWhileLoopElementImpl(
            Optional<CodeElement.CodeBlock> parent,
            CodeElement.ExpressionElement condition,
            CodeElement.CodeBlock block
    ) implements CodeElement.DoWhileLoopElement {}

    public record WhileLoopElementImpl(
            Optional<CodeElement.CodeBlock> parent,
            CodeElement.ExpressionElement condition,
            CodeElement.CodeBlock block
    ) implements CodeElement.WhileLoopElement {}

    public record ForLoopElementImpl(
            Optional<CodeElement.CodeBlock> parent,
            Either<VariableDeclarationStatement, AssignmentExpression> variable,
            ExpressionElement condition,
            ExpressionElement incrementor,
            CodeBlock block
    ) implements CodeElement.ForLoopElement {}

    public record ForEachLoopElementImpl(
            Optional<CodeElement.CodeBlock> parent,
            CodeElement.VariableDeclarationStatement variable,
            CodeElement.ExpressionElement iterable,
            CodeElement.CodeBlock block
    ) implements CodeElement.ForEachLoopElement {}
}