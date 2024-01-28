package io.github.username.projectname.components.sections

import AppearanceAwareImage
import IconButtonNoHover
import androidx.compose.runtime.Composable
import io.github.username.projectname.Config
import io.github.username.projectname.components.widgets.GlassBox
import io.github.username.projectname.util.FontHandler
import io.github.username.projectname.util.LinkableImage
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.ResponsiveValues
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1

@Composable
fun SoftwareAndSkills() {
    // These are the boxes we want to create.
    val data = Config.SoftwareAndSkills.getMap()

    H1 { SpanText("Software, Tools & Skills", modifier = Modifier.fontFamily(FontHandler.getFont("h1"))) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {

            SimpleGrid(
                modifier = Modifier
                    .fillMaxHeight().fillMaxWidth(), // TODO: Is this necessary?
                numColumns = ResponsiveValues(
                    base = 1,
                    sm = 2,
                    md = 2,
                    lg = 2,
                    xl = 3
                )
            ) {
                // Now create a GlassBox for each one.
                for ((name, linkeableList) in data) createGlassBoxContainer(name, linkeableList)
            }
        }
    }
}

@Composable
fun createGlassBoxContainer(name: String, linkableImages: Array<LinkableImage>) {
    val ctx = rememberPageContext()

    println("Creating Box Container for: $name")

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpanText(name, modifier = Modifier.fontFamily(FontHandler.getFont("h1")))

        GlassBox(
            modifier = Modifier.margin(all = 2.cssRem)
        ) {
            SimpleGrid(
                modifier = Modifier.padding(all = 1.cssRem),
                numColumns = numColumns(base = 3, sm = 3, md = 3, lg = 4)
            ) {
                for (linkable in linkableImages) {
                    println("\tCreating image for ${linkable.imageSrc}")

                    GlassBox(
                        modifier = Modifier.size(65.px)
                            .margin(all = 0.6.cssRem)
                    ) {
                        IconButtonNoHover(
                            onClick = { ctx.router.navigateTo(linkable.url) }
                        ) {
                            AppearanceAwareImage(
                                src = linkable.imageSrc,
                                noChange = true,
                                modifier = Modifier.size(42.px)
                            )
                        }
                    }
                }
            }
        }
    }
}