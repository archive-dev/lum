package ru.whoisamyy.lum.intellijplugin.psi

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.PsiElement
import lum.core.parsing.antlr4.LumLexer
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode
import ru.whoisamyy.lum.intellijplugin.LumLexerAdapter

class LumFunctionDeclarationImpl(node: ASTNode) : ANTLRPsiNode(node), LumNamedElement {
    override fun getName(): String? {
        return nameIdentifier?.text
    }

    override fun setName(name: @NlsSafe String): PsiElement? {
        return nameIdentifier
    }

    override fun getNameIdentifier(): PsiElement? {
        return findChildByType(LumLexerAdapter().getTokenType(LumLexer.IDENTIFIER)!!)
    }
}