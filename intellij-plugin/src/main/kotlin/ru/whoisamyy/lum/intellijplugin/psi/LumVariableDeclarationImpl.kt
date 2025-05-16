package ru.whoisamyy.lum.intellijplugin.psi

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.PsiElement
import lum.core.parsing.antlr4.LumLexer
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode
import ru.whoisamyy.lum.intellijplugin.LumLexerAdapter

class LumVariableDeclarationImpl(node: ASTNode) : ANTLRPsiNode(node), LumNamedElement {
    override fun getNameIdentifier(): PsiElement? {
        return findChildByType(LumLexerAdapter().getTokenType(LumLexer.IDENTIFIER)!!)
    }

    override fun setName(name: @NlsSafe String): PsiElement? {
        return this
    }
}