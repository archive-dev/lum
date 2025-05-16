package ru.whoisamyy.lum.intellijplugin

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object LumFileType : LanguageFileType(LumLanguage) {
    override fun getName(): String = "Lum File"
    override fun getDescription(): String = "Lum language file"
    override fun getDefaultExtension(): String = "lum" // Расширение файла
    override fun getIcon(): Icon? = null // Загрузка иконки (см. шаг 6)
}