package ru.whoisamyy.lum.intellijplugin.psi

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiReference
import com.intellij.psi.util.PsiTreeUtil
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode

class LumReferenceExpressionImpl(node: ASTNode) : ANTLRPsiNode(node), LumElement, PsiReference {
    override fun getElement(): PsiElement = this // Ссылка на сам PSI элемент

    override fun getReference(): PsiReference? = this // Этот класс сам является ссылкой

    // Диапазон текста, который является ссылкой (обычно весь текст узла)
    override fun getRangeInElement(): TextRange = TextRange(0, node.textLength)

    // Имя, на которое ссылаемся
    fun getReferencedName(): String = node.text

    /**
     * Главный метод: Находит объявление, на которое указывает эта ссылка.
     * Это основа для Ctrl+Click и Find Usages.
     */
    override fun resolve(): PsiElement? {
        // Логика разрешения ссылки:
        // 1. Найти имя (getReferencedName())
        // 2. Искать объявление (LumNamedElement) с таким именем вверх по дереву PSI
        //    в правильной области видимости (scope).
        val nameToResolve = getReferencedName()
        var currentScope: PsiElement? = this

        while (currentScope != null) {
            // Ищем объявления внутри текущего scope
            val declarations = PsiTreeUtil.findChildrenOfType(currentScope, LumNamedElement::class.java)
            val found = declarations.firstOrNull { decl ->
                // Важно: Объявление должно быть *до* ссылки и иметь то же имя
                decl.name == nameToResolve && decl.textOffset < this.textOffset
                // Дополнительно: Проверка типа (переменная/функция) если необходимо
            }

            if (found != null) {
                return found // Нашли!
            }

            // Если не нашли, поднимаемся к родительскому скоупу
            // Логика определения "скоупа" зависит от языка (блок, функция, файл)
            currentScope = PsiTreeUtil.getParentOfType(
                currentScope,
                /* LumBlock::class.java, LumFunctionDeclarationImpl::class.java, */ // Ваши классы для скоупов
                PsiFile::class.java // До файла
            )
            // Останавливаемся, если вышли за пределы файла
            if (currentScope is PsiFile && currentScope != this.containingFile) break
        }

        return null // Не смогли разрешить ссылку
    }

    // Канонический текст ссылки (обычно просто имя)
    override fun getCanonicalText(): String = node.text

    // Вызывается при переименовании элемента, на который указывает ссылка
    override fun handleElementRename(newElementName: String): PsiElement {
        // Заменить текст этого узла на newElementName
        // Требует утилиту для модификации PSI (например, через PsiFileFactory)
        // val factory = PsiFileFactory.getInstance(project)
        // val newId = factory.createIdentifier(newElementName) // Нужен метод для создания идентификатора
        // this.node.replaceChild(this.node.firstChildNode, newId.node)
        // Пока просто вернем this, переименование требует больше деталей
        // TODO: Реализовать корректное переименование
        return this
    }

    // Вызывается при перемещении элемента
    override fun bindToElement(element: PsiElement): PsiElement {
        // Если элемент перемещен в другой файл/модуль, может потребоваться
        // добавить импорты или изменить квалифицированное имя.
        // TODO: Реализовать, если применимо
        return this
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        // Оптимизированная проверка, указывает ли эта ссылка на данный `element`
        return resolve() == element
    }

    // Для автодополнения *в этом месте*
    override fun getVariants(): Array<Any> {
        // Можно вернуть варианты автодополнения специфичные для этого контекста,
        // но часто делегируют общему CompletionContributor.
        // Можно, например, найти все видимые переменные/функции и создать LookupElement'ы.
        return emptyArray() // Пока оставим пустым
    }

    override fun isSoft(): Boolean = false // Не "мягкая" ссылка (обычно false)
}