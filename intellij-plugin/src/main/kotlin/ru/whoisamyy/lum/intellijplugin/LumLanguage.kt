package ru.whoisamyy.lum.intellijplugin

import com.intellij.lang.Language

object LumLanguage : Language("Lum"){
    fun readResolve(): Any = LumLanguage
    override fun isCaseSensitive() = true
    override fun getDisplayName(): String = "Lum"
}