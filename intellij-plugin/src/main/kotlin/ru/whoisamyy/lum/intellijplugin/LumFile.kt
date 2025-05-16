package ru.whoisamyy.lum.intellijplugin

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class LumFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, LumLanguage) {
    override fun getFileType(): FileType = LumFileType
    override fun toString(): String = "Lum Language File"
    // Иконку можно получить из FileType
    // override fun getIcon(flags: Int): Icon? = super.getIcon(flags)
}