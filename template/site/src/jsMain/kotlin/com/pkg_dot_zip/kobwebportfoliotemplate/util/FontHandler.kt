package com.pkg_dot_zip.kobwebportfoliotemplate.util

object FontHandler {

    private val logger = Logger.get { }

    // Fonts.
    object Font {
        const val ARIAL = "Arial"

        // These are added in the build.gradle.kts file.
        const val INTER = "Inter"
        const val BITTER = "Bitter"
        const val RALEWAY = "Raleway"
        const val ROKKITT = "Rokkitt"
        const val JOST = "Jost"
        const val MAJOR_MONO_DISPLAY = "Major Mono Display"
    }

    private val fontMap: Map<String, String> = mapOf(
        "sitename" to Font.MAJOR_MONO_DISPLAY,
        "headersitemap" to Font.ARIAL,
        "h1" to Font.INTER,
        "text" to Font.INTER,
        "repotext" to Font.INTER,
        "repostarwatch" to Font.MAJOR_MONO_DISPLAY,
        "footer" to Font.INTER,
    )

    fun getFont(key: String): String {
        if (!fontMap.containsKey(key)) {
            logger.error("No font declared for $key")
            throw IllegalArgumentException()
        }
        return fontMap[key.lowercase().trim()]!!
    }
}