package ru.whoisamyy.lum.intellijplugin.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import lum.core.parsing.antlr4.LumLexer
import org.antlr.intellij.adaptor.lexer.TokenIElementType
import ru.whoisamyy.lum.intellijplugin.LumLexerAdapter
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as DefaultColors

class LumSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        // Определяем ключи атрибутов текста для разных типов токенов
        // Имена ключей лучше делать уникальными для языка ("LUM.")
        val KEYWORD = TextAttributesKey.createTextAttributesKey("LUM.KEYWORD", DefaultColors.KEYWORD)
        val IDENTIFIER = TextAttributesKey.createTextAttributesKey("LUM.IDENTIFIER", DefaultColors.IDENTIFIER)
        val NUMBER = TextAttributesKey.createTextAttributesKey("LUM.NUMBER", DefaultColors.NUMBER)
        val STRING = TextAttributesKey.createTextAttributesKey("LUM.STRING", DefaultColors.STRING)
        val LINE_COMMENT = TextAttributesKey.createTextAttributesKey("LUM.LINE_COMMENT", DefaultColors.LINE_COMMENT)
        val BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("LUM.BLOCK_COMMENT", DefaultColors.BLOCK_COMMENT)
        val OPERATOR = TextAttributesKey.createTextAttributesKey("LUM.OPERATOR", DefaultColors.OPERATION_SIGN)
        val DELIMITER = TextAttributesKey.createTextAttributesKey("LUM.DELIMITER", DefaultColors.PARENTHESES) // Скобки, запятые и т.д.
        val BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("LUM.BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        // Массивы нужны, т.к. getTokenHighlights возвращает TextAttributesKey[]
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val STRING_KEYS = arrayOf(STRING)
        private val LINE_COMMENT_KEYS = arrayOf(LINE_COMMENT)
        private val BLOCK_COMMENT_KEYS = arrayOf(BLOCK_COMMENT)
        private val OPERATOR_KEYS = arrayOf(OPERATOR)
        private val DELIMITER_KEYS = arrayOf(DELIMITER)
        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }

    override fun getHighlightingLexer(): Lexer = LumLexerAdapter()

    // Главный метод: сопоставляет тип токена с ключом атрибутов текста
    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        if (tokenType !is TokenIElementType) return EMPTY_KEYS

        // Используем ANTLR тип токена для определения стиля
        return when (tokenType.antlrTokenType) {
            // Keywords
            LumLexer.AS,
            LumLexer.SUPER,
            LumLexer.PACKAGE,
            LumLexer.IMPORT,
            LumLexer.FROM,
            LumLexer.BREAK,
            LumLexer.CONTINUE,
            LumLexer.RETURN,
            LumLexer.SWITCH,
            LumLexer.CASE,
            LumLexer.DEFAULT,
            LumLexer.IF,
            LumLexer.ELIF,
            LumLexer.ELSE,
            LumLexer.WHILE,
            LumLexer.DO,
            LumLexer.FOR,
            LumLexer.IN,
            LumLexer.GET,
            LumLexer.SET,
            LumLexer.FUNC,
            LumLexer.INIT,
            LumLexer.OPERATOR,
            LumLexer.CLASS,
            LumLexer.EXTENDS,
            LumLexer.IMPLEMENTS,
            LumLexer.INTERFACE,
            LumLexer.ANNOTATION,
            LumLexer.PUBLIC,
            LumLexer.PRIVATE,
            LumLexer.PROTECTED,
            LumLexer.STATIC,
            LumLexer.ABSTRACT,
            LumLexer.FINAL
                 -> KEYWORD_KEYS
            // Literals
            LumLexer.NUMBER -> NUMBER_KEYS
            LumLexer.STRING -> STRING_KEYS
            // Comments
            LumLexer.COMMENT -> LINE_COMMENT_KEYS
            // Operators
            LumLexer.MUL,
            LumLexer.DIV,
            LumLexer.T__11,
            LumLexer.MOD,
            LumLexer.ADD,
            LumLexer.SUB,
            LumLexer.SHR,
            LumLexer.SHL,
            LumLexer.XOR,
            LumLexer.GT,
            LumLexer.LT,
            LumLexer.GE,
            LumLexer.LE,
            LumLexer.EQUAL_EQUAL,
            LumLexer.NOT_EQUAL,
            LumLexer.IS,
            LumLexer.AND,
            LumLexer.OR,
            LumLexer.NOT,
            LumLexer.INC,
            LumLexer.DEC
                -> OPERATOR_KEYS
            // Delimiters
            LumLexer.LBRACE, LumLexer.RBRACE,
            LumLexer.LBRACK, LumLexer.RBRACK,
            LumLexer.LPAREN, LumLexer.RPAREN
                     -> DELIMITER_KEYS
            // Identifier
            LumLexer.IDENTIFIER -> IDENTIFIER_KEYS

            // Обработка ошибок лексера (если есть правило для них)
            // ANTLRLexer.Token.INVALID_TYPE -> BAD_CHAR_KEYS

            else -> EMPTY_KEYS // Для всех остальных токенов (например, WS, если он не skip)
        }
    }
}