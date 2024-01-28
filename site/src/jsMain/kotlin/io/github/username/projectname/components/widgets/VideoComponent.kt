package io.github.username.projectname.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.dom.Iframe

@Composable
fun VideoComponent(embedSrc: String, height: Int = 0, width: Int = 0) {
    // TODO: Clean up.

    // Experimental sizing.
    var height2 = height
    var width2 = width

    if (height == 0 || width == 0) {
        println("VideoComponent for $embedSrc had some params missing. Trying to size automatically.")

        val breakpoint = rememberBreakpoint()
        println("Sizing VideoComponent for ${breakpoint.name}")

        // TODO: Find calculation that works dependent on the size of the screen.
        val maxVideoHeight: Int = 316
        val maxVideoWidth: Int = 515

        // Because we do not ALWAYS put the Video next to the AboutMeSection, a smaller breakpoint might have a bigger video.
        when (breakpoint) {
            Breakpoint.ZERO -> {
                height2 = maxVideoHeight - 50
                width2 = maxVideoWidth - 200
            }

            Breakpoint.SM -> {
                height2 = maxVideoHeight
                width2 = maxVideoWidth
            }

            Breakpoint.MD -> {
                height2 = maxVideoHeight
                width2 = maxVideoWidth
            }

            Breakpoint.LG -> {
                height2 = maxVideoHeight - 30
                width2 = maxVideoWidth - 100
            }

            Breakpoint.XL -> {
                height2 = maxVideoHeight
                width2 = maxVideoWidth
            }

            else -> {
                println("Couldn't size VideoComponent for ${breakpoint.name}")
                height2 = 316
                width2 = 515
            }
        }
    }

    Iframe(attrs = {
        attr("src", embedSrc)
        attr("width", "$width2")
        attr("height", "$height2")
    })
}