package io.github.username.projectname.util

import io.github.username.projectname.Config

object FontHandler {

    private val fontMap: Map<String, String> = Config.Fonts.FONT_MAP

    fun getFont(key: String): String {
        if (!fontMap.containsKey(key)) {
            println("No font declared for $key")
            throw IllegalArgumentException()
        }
        return fontMap[key.lowercase().trim()]!!
    }
}