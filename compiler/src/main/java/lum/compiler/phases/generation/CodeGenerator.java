package lum.compiler.phases.generation;

import lum.core.ir.CodeElement;

public interface CodeGenerator {
    default void handleCodeBlock(CodeElement.CodeBlock block) {
        for (var element : block.elements()) {
            handleCodeElement(element);
        }
    }

    default void handleCodeElement(CodeElement element) {
        switch (element) {
            case CodeElement.ExpressionElement expr -> handleExpressionElement(expr);
            case CodeElement.StatementElement stmt -> handleStatementElement(stmt);
            default -> throw new IllegalStateException("Unexpected value: " + element);
        }
    }

    default void handleExpressionElement(CodeElement.ExpressionElement expressionElement) {
        switch (expressionElement) {
            case CodeElement.ArrayExpression arrayExpression -> handleArrayExpression(arrayExpression);
            case CodeElement.DictExpression dictExpression -> handleDictExpression(dictExpression);
            case CodeElement.LambdaExpression lambdaExpression -> handleLambdaExpression(lambdaExpression);
            case CodeElement.SuperAccessExpression superAccessExpression -> handleSuperAccessExpression(superAccessExpression);
            case CodeElement.IdentifierExpression identifierExpression -> handleIdentifierExpression(identifierExpression);
            case CodeElement.LiteralExpression<?> literalExpression -> handleLiteralExpression(literalExpression);
            case CodeElement.AssignmentExpression assignmentExpression -> handleAssignmentExpression(assignmentExpression);
            case CodeElement.BinaryExpression binaryExpression -> handleBinaryExpression(binaryExpression);
            case CodeElement.UnaryPrefixExpression unaryPrefixExpression -> handleUnaryPrefixExpression(unaryPrefixExpression);
            case CodeElement.UnaryPostfixExpression unaryPostfixExpression -> handleUnaryPostfixExpression(unaryPostfixExpression);
            case CodeElement.CastExpression castExpression -> handleCastExpression(castExpression);
            case CodeElement.NewExpression newExpression -> handleNewExpression(newExpression);
            case CodeElement.CallExpression callExpression -> handleCallExpression(callExpression);
            case CodeElement.MemberAccessExpression memberAccessExpression -> handleMemberAccessExpression(memberAccessExpression);
            case CodeElement.ArrayAccessExpression arrayAccessExpression -> handleArrayAccessExpression(arrayAccessExpression);
            default -> throw new IllegalStateException("Unexpected value: " + expressionElement);
        }
    }
    void handleArrayExpression(CodeElement.ArrayExpression arrayExpression);
    void handleDictExpression(CodeElement.DictExpression dictExpression);
    void handleLambdaExpression(CodeElement.LambdaExpression lambdaExpression);
    void handleIdentifierExpression(CodeElement.IdentifierExpression identifierExpression);
    void handleSuperAccessExpression(CodeElement.SuperAccessExpression superAccessExpression);
    void handleAssignmentExpression(CodeElement.AssignmentExpression assignmentExpression);
    void handleBinaryExpression(CodeElement.BinaryExpression binaryExpression);
    void handleUnaryPrefixExpression(CodeElement.UnaryPrefixExpression unaryPrefixExpression);
    void handleUnaryPostfixExpression(CodeElement.UnaryPostfixExpression unaryPostfixExpression);
    void handleCastExpression(CodeElement.CastExpression castExpression);
    void handleCallExpression(CodeElement.CallExpression callExpression);
    void handleNewExpression(CodeElement.NewExpression newExpression);
    void handleMemberAccessExpression(CodeElement.MemberAccessExpression memberAccessExpression);
    void handleArrayAccessExpression(CodeElement.ArrayAccessExpression arrayAccessExpression);
    default void handleLiteralExpression(CodeElement.LiteralExpression<?> literalExpression) {
        switch (literalExpression) {
            case CodeElement.TrueExpr true_ -> handleTrueExpr(true_);
            case CodeElement.FalseExpr false_ -> handleFalseExpr(false_);
            case CodeElement.NullExpr null_ -> handleNullExpr(null_);
            case CodeElement.StringExpr string -> handleStringExpr(string);
            case CodeElement.NumberExpr<?> number -> handleNumberExpr(number);
            default -> throw new IllegalStateException("Unexpected value: " + literalExpression);
        }
    }
    void handleTrueExpr(CodeElement.TrueExpr trueExpr);
    void handleFalseExpr(CodeElement.FalseExpr falseExpr);
    void handleNullExpr(CodeElement.NullExpr nullExpr);
    void handleStringExpr(CodeElement.StringExpr stringExpr);
    void handleNumberExpr(CodeElement.NumberExpr<?> numberExpr);

    default void handleStatementElement(CodeElement.StatementElement statementElement) {
        switch (statementElement) {
            case CodeElement.ControlStatement controlStatement -> handleControlStatement(controlStatement);
            case CodeElement.VariableDeclarationStatement variableDeclarationStatement -> handleVariableDeclarationStatement(variableDeclarationStatement);
            default -> throw new IllegalStateException("Unexpected value: " + statementElement);
        }
    }
    void handleVariableDeclarationStatement(CodeElement.VariableDeclarationStatement variableDeclarationStatement);
    default void handleControlStatement(CodeElement.ControlStatement controlStatement) {
        switch (controlStatement) {
            case CodeElement.IfStatement ifStatement -> handleIfStatement(ifStatement);
            case CodeElement.SwitchStatement switchStatement -> handleSwitchStatement(switchStatement);
            case CodeElement.ReturnStatement returnStatement -> handleReturnStatement(returnStatement);
            case CodeElement.BreakStatement breakStatement -> handleBreakStatement(breakStatement);
            case CodeElement.ContinueStatement continueStatement -> handleContinueStatement(continueStatement);
            case CodeElement.ForEachLoopElement forEachLoopElement -> handleForEachLoopElement(forEachLoopElement);
            case CodeElement.ForLoopElement forLoopElement -> handleForLoopElement(forLoopElement);
            case CodeElement.DoWhileLoopElement doWhileLoopElement -> handleDoWhileLoopElement(doWhileLoopElement);
            case CodeElement.WhileLoopElement whileLoopElement -> handleWhileLoopElement(whileLoopElement);
            case CodeElement.LoopElement loopElement -> handleLoopElement(loopElement);
            default -> throw new IllegalStateException("Unexpected value: " + controlStatement);
        }
    }
    void handleIfStatement(CodeElement.IfStatement ifStatement);
    void handleSwitchStatement(CodeElement.SwitchStatement switchStatement);
    void handleReturnStatement(CodeElement.ReturnStatement returnStatement);
    void handleBreakStatement(CodeElement.BreakStatement breakStatement);
    void handleContinueStatement(CodeElement.ContinueStatement continueStatement);
    void handleLoopElement(CodeElement.LoopElement loopElement);
    void handleDoWhileLoopElement(CodeElement.DoWhileLoopElement doWhileLoopElement);
    void handleWhileLoopElement(CodeElement.WhileLoopElement whileLoopElement);
    void handleForLoopElement(CodeElement.ForLoopElement forLoopElement);
    void handleForEachLoopElement(CodeElement.ForEachLoopElement forEachLoopElement);
}
