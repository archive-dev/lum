package ru.whoisamyy.lum.intellijplugin

// Важно: Импортируем сгенерированный ANTLR лексер
import lum.core.parsing.antlr4.LumLexer
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor

class LumLexerAdapter : ANTLRLexerAdaptor(LumLanguage, LumLexer(null)) {
    // ANTLRLexerAdaptor делает основную работу по адаптации
}