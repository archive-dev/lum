package lum.compiler.phases.generation;

import lum.core.model.ClassModel;
import lum.core.parsing.antlr4.LumParser;

import java.util.Optional;

public interface CodeHandler {
    void handleBlock(LumParser.BlockContext ctx);

    default Optional<ClassModel> handleStatement(LumParser.StatementContext context) {
        switch (context.getChild(0)) {
            case LumParser.DeclarationContext ctx_ -> {
                return handleDeclaration(ctx_);
            }
            case LumParser.ExpressionContext ctx_ -> {
                return Optional.of(handleExpression(ctx_));
            }
            case LumParser.ControlStatementContext ctx_ -> handleControlStatement(ctx_);
            case LumParser.BreakContext ctx_ -> handleBreak(ctx_);
            case LumParser.ContinueContext ctx_ -> handleContinue(ctx_);
            case LumParser.ReturnContext ctx_ -> handleReturn(ctx_);
            case null, default -> throw new IllegalStateException("Unexpected value: " + context.getChild(0));
        }
        return Optional.empty();
    }

    // Handled by models
    // void handlePackageStatement(LumParser.PackageContext ctx);
    // void handleImportStatement(LumParser.ImportStatementContext ctx);

    default void handleControlStatement(LumParser.ControlStatementContext ctx) {
        switch (ctx.getChild(0)) {
            case LumParser.IfStatementContext ctx_ -> handleIfStatement(ctx_);
            case LumParser.SwitchStatementContext ctx_ -> handleSwitchStatement(ctx_);
            case LumParser.LoopContext ctx_ -> handleLoop(ctx_);

            case null, default -> throw new IllegalStateException("Unexpected value: " + ctx.getChild(0));
        }
    }
    void handleIfStatement(LumParser.IfStatementContext ctx);
    void handleSwitchStatement(LumParser.SwitchStatementContext ctx);
    default void handleLoop(LumParser.LoopContext ctx) {
        switch (ctx.getChild(0)) {
            case LumParser.WhileLoopStatementContext while_ -> handleWhileLoopStatement(while_);
            case LumParser.ForLoopStatementContext for_ -> handleForLoopStatement(for_);

            case null, default -> throw new IllegalStateException("Unexpected value: " + ctx.getChild(0));
        }
    }

    default void handleWhileLoopStatement(LumParser.WhileLoopStatementContext ctx) {
        switch (ctx.getChild(0)) {
            case LumParser.WhileLoopContext ctx_ -> handleWhileLoop(ctx_);
            case LumParser.DoWhileLoopContext ctx_ -> handleDoWhileLoop(ctx_);
            case null, default -> throw new IllegalStateException("Unexpected value: " + ctx.getChild(0));
        }
    }
    void handleWhileLoop(LumParser.WhileLoopContext ctx);
    void handleDoWhileLoop(LumParser.DoWhileLoopContext ctx);

    default void handleForLoopStatement(LumParser.ForLoopStatementContext ctx) {
        switch (ctx.getChild(0)) {
            case LumParser.ForILoopContext ctx_ -> handleForILoop(ctx_);
            case LumParser.ForEachLoopContext ctx_ -> handleForEachLoop(ctx_);
            case null, default -> throw new IllegalStateException("Unexpected value: " + ctx.getChild(0));
        }
    }
    void handleForILoop(LumParser.ForILoopContext ctx);
    void handleForEachLoop(LumParser.ForEachLoopContext ctx);

    void handleBreak(LumParser.BreakContext ctx);
    void handleContinue(LumParser.ContinueContext ctx);
    void handleReturn(LumParser.ReturnContext ctx);


    default Optional<ClassModel> handleDeclaration(LumParser.DeclarationContext ctx) {
        switch (ctx.getChild(0)) {
            case LumParser.FunctionDeclarationContext ctx_ -> handleFunctionDeclaration(ctx_);
            case LumParser.VariableDeclarationStatementContext ctx_ -> {
                return Optional.of(handleVariableDeclarationStatement(ctx_));
            }
            case LumParser.OperatorDeclarationContext ctx_ -> handleOperatorDeclaration(ctx_);
            case LumParser.ConstructorDeclarationContext ctx_ -> handleConstructorDeclaration(ctx_);
            case LumParser.FunctionSignatureContext ctx_ -> handleFunctionSignature(ctx_);
            case LumParser.ClassDeclarationContext ctx_ -> handleClassDeclaration(ctx_);
            case LumParser.InterfaceDeclarationContext ctx_ -> handleInterfaceDeclaration(ctx_);
            case LumParser.AnnotationDeclarationContext ctx_ -> handleAnnotationDeclaration(ctx_);

            case null, default -> throw new IllegalStateException("Unexpected value: " + ctx.getChild(0));
        }

        return Optional.empty();
    }

    void handleFunctionDeclaration(LumParser.FunctionDeclarationContext ctx);
    ClassModel handleVariableDeclarationStatement(LumParser.VariableDeclarationStatementContext ctx);
    void handleOperatorDeclaration(LumParser.OperatorDeclarationContext ctx);
    void handleConstructorDeclaration(LumParser.ConstructorDeclarationContext ctx);
    void handleFunctionSignature(LumParser.FunctionSignatureContext ctx);
    void handleClassDeclaration(LumParser.ClassDeclarationContext ctx);
    void handleInterfaceDeclaration(LumParser.InterfaceDeclarationContext ctx);
    void handleAnnotationDeclaration(LumParser.AnnotationDeclarationContext ctx);


    default ClassModel handleExpression(LumParser.ExpressionContext ctx) {
        return switch (ctx) {
            case LumParser.PrimaryExpressionContext primary -> handlePrimaryExpression(primary);
            case LumParser.LambdaExpressionContext lambda -> handleLambdaExpression(lambda);
            case LumParser.FunctionCallExprContext functionCall -> handleFunctionCallExpr(functionCall);
            case LumParser.ArrayAccessContext arrayAccess -> handleArrayAccess(arrayAccess);
            case LumParser.MemberAccessContext memberAccess -> handleMemberAccess(memberAccess);
            case LumParser.PostUnaryContext postUnary -> handlePostUnary(postUnary);
            case LumParser.PreUnaryContext preUnary -> handlePreUnary(preUnary);
            case LumParser.BinaryContext binary -> handleBinary(binary);
            case null, default -> throw new IllegalStateException("Unexpected value: " + ctx);
        };
    }

    default ClassModel handlePrimaryExpression(LumParser.PrimaryExpressionContext ctx) {
        return switch (ctx.primary()) {
            case LumParser.LiteralExprContext ctx_ -> handleLiteralExpr(ctx_);
            case LumParser.IdentifierExprContext ctx_ -> handleIdentifierExpr(ctx_);
            case LumParser.AssignmentExprContext ctx_ -> handleAssignmentExpr(ctx_);
            case LumParser.ListLiteralContext ctx_ -> handleListLiteral(ctx_);
            case LumParser.DictLiteralContext ctx_ -> handleDictLiteral(ctx_);
            case LumParser.ParenExprContext ctx_ -> handleParenExpr(ctx_);
            case null, default -> throw new IllegalStateException("Unexpected value: " + ctx.primary());
        };
    }

    default ClassModel handleLiteralExpr(LumParser.LiteralExprContext ctx) {
        return switch (ctx.literal()) {
            case LumParser.NumContext num -> handleNumberLiteral(num);
            case LumParser.StrContext str -> handleStringLiteral(str);
            case LumParser.NullContext _ -> handleNullLiteral();
            case LumParser.TrueContext _ -> handleTrueLiteral();
            case LumParser.FalseContext _ -> handleFalseLiteral();
            default -> throw new IllegalStateException("Unexpected value: " + ctx.literal());
        };
    }

    ClassModel handleFalseLiteral();
    ClassModel handleTrueLiteral();
    ClassModel handleNullLiteral();

    ClassModel handleNumberLiteral(LumParser.NumContext number);
    ClassModel handleStringLiteral(LumParser.StrContext string);

    ClassModel handleIdentifierExpr(LumParser.IdentifierExprContext ctx);
    ClassModel handleAssignmentExpr(LumParser.AssignmentExprContext ctx);
    ClassModel handleListLiteral(LumParser.ListLiteralContext ctx);
    ClassModel handleDictLiteral(LumParser.DictLiteralContext ctx);
    ClassModel handleParenExpr(LumParser.ParenExprContext ctx);

    ClassModel handleLambdaExpression(LumParser.LambdaExpressionContext ctx);
    ClassModel handleFunctionCallExpr(LumParser.FunctionCallExprContext ctx);
    ClassModel handleArrayAccess(LumParser.ArrayAccessContext ctx);
    ClassModel handleMemberAccess(LumParser.MemberAccessContext ctx);
    ClassModel handlePostUnary(LumParser.PostUnaryContext ctx);
    ClassModel handlePreUnary(LumParser.PreUnaryContext ctx);
    ClassModel handleBinary(LumParser.BinaryContext ctx);
}
