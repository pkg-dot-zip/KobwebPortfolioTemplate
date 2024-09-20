package com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets

import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.Res
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Video

@Composable
fun VideoBackground(src: String = Res.Video.CODING, id: String = "videoBackground", opacity: Double = 0.03, zIndex: Int = 0) {
    val videoType = when (src.substringAfterLast('.', "")) {
        "mp4" -> "video/mp4"
        "webm" -> "video/webm"
        "ogg" -> "video/ogg"
        else -> "video/mp4" // Default to mp4 if unknown or no extension.
    }

    Video(Modifier.attrsModifier {
        attr("autoplay", "")
        attr("muted", "")
        attr("loop", "")
    }.id(id).styleModifier {
        position(Position.Fixed)
        objectFit(ObjectFit.Cover)
        left(0.px)
        right(0.px)
        top(0.px)
        bottom(0.px)
        minWidth(100.vw)
        minHeight(100.vh)
        zIndex(zIndex)
        opacity(opacity)
        pointerEvents(PointerEvents.None)
    }.toAttrs()) {
        Source(
            attrs = {
                attr("src", src)
                attr("type", videoType)
            },
        )
    }
}