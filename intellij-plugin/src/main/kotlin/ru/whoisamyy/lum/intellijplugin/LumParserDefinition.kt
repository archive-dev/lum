package ru.whoisamyy.lum.intellijplugin

// Импортируем типы токенов и правил из сгенерированного парсера
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import lum.core.parsing.antlr4.LumLexer
import lum.core.parsing.antlr4.LumParser
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.RuleIElementType
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import ru.whoisamyy.lum.intellijplugin.psi.LumFunctionDeclarationImpl
import ru.whoisamyy.lum.intellijplugin.psi.LumReferenceExpressionImpl

class LumParserDefinition : ParserDefinition {

    init {
        // Важно: Инициализируем фабрику для регистрации токенов и правил
        // Это должно быть сделано один раз перед использованием PSIElementTypeFactory
        @Suppress("DEPRECATION")
        PSIElementTypeFactory.defineLanguageIElementTypes(
            LumLanguage,
            LumParser.tokenNames, // Массив имен токенов из сгенерированного парсера
            LumParser.ruleNames   // Массив имен правил из сгенерированного парсера
        )
    }

    // Тип корневого элемента файла PSI
    val FILE = IFileElementType(LumLanguage)

    override fun createLexer(project: Project?): Lexer = LumLexerAdapter()

    override fun createParser(project: Project?): PsiParser {
        val parser = LumParser(null) // Создаем экземпляр ANTLR парсера
        return object : ANTLRParserAdaptor(LumLanguage, parser) {
            override fun parse(parser: Parser, root: IElementType): ParseTree {
                // Указываем стартовое правило грамматики
                if (root is IFileElementType) {
                    return (parser as LumParser).program() // Вызываем ваше стартовое правило
                }
                // Сюда не должны попадать при парсинге файла
                throw UnsupportedOperationException("Cannot parse ${root.javaClass.name}")
            }
        }
    }

    override fun getFileNodeType(): IFileElementType = FILE

    // Определяем наборы токенов для различных целей
    override fun getCommentTokens(): TokenSet = PSIElementTypeFactory.createTokenSet(
        LumLanguage,
        LumLexer.COMMENT
    )

    override fun getStringLiteralElements(): TokenSet = PSIElementTypeFactory.createTokenSet(
        LumLanguage,
        LumLexer.STRING
    )

    override fun getWhitespaceTokens(): TokenSet = PSIElementTypeFactory.createTokenSet(
        LumLanguage,
        LumLexer.WS
    )

    // Создание элементов PSI дерева. Для начала можно использовать базовый ANTLRPsiNode.
    // Для более сложных фич (рефакторинг, поиск использований) нужно будет создавать
    // свои классы PSI элементов для конкретных правил грамматики.
    override fun createElement(node: ASTNode): PsiElement {
        val elementType = node.elementType
        // Если это элемент правила (не токен)
        if (elementType is RuleIElementType) {
            when (elementType.ruleIndex) {
                LumParser.RULE_functionDeclaration, LumParser.RULE_functionSignature,
                LumParser.RULE_operatorDeclaration, LumParser.RULE_constructorDeclaration -> return LumFunctionDeclarationImpl(node)

                LumParser.IDENTIFIER -> {
                    val parentType = node.treeParent.elementType
                    if (parentType is RuleIElementType)
                        when (parentType.ruleIndex) {
                            LumParser.RULE_functionDeclaration, LumParser.RULE_functionSignature,
                            LumParser.RULE_operatorDeclaration, LumParser.RULE_constructorDeclaration,
                            LumParser.RULE_variableDeclaration -> return ANTLRPsiNode(node)

                            else -> {
                                LumReferenceExpressionImpl(node)
                            }
                        }
                }
            }
        }
        // Для токенов и по умолчанию возвращаем базовый узел
        return ANTLRPsiNode(node)
    }

    // Создание корневого элемента PSI файла
    override fun createFile(viewProvider: FileViewProvider): PsiFile = LumFile(viewProvider)
}