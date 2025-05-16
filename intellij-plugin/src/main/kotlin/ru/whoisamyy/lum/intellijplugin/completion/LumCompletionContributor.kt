package ru.whoisamyy.lum.intellijplugin.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import ru.whoisamyy.lum.intellijplugin.LumLanguage

class LumCompletionContributor : CompletionContributor() {
    private val keywords = listOf(
        "as",
        "super",
        "package",
        "import",
        "from",
        "break",
        "continue",
        "return",
        "switch",
        "case",
        "default",
        "if",
        "elif",
        "else",
        "while",
        "do",
        "for",
        "in",
        "get",
        "set",
        "func",
        "init",
        "operator",
        "class",
        "extends",
        "implements",
        "interface",
        "annotation",
        "public",
        "private",
        "protected",
        "static",
        "abstract",
        "final"
    )

    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiFile().withLanguage(LumLanguage),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    for (keyword in keywords) {
                        result.addElement(LookupElementBuilder.create(keyword).withBoldness(true).withTypeText("keyword"))
                    }
                }
            }
        )
    }
}