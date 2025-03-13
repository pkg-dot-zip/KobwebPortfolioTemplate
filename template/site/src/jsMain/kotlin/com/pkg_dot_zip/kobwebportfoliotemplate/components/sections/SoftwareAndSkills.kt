package com.pkg_dot_zip.kobwebportfoliotemplate.components.sections

import AppearanceAwareImage
import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.Res
import com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets.GlassBox
import com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets.IconButtonWithHover
import com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets.scaleOnHoverAnimation
import com.pkg_dot_zip.kobwebportfoliotemplate.util.FontHandler
import com.pkg_dot_zip.kobwebportfoliotemplate.util.Logger
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.window
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1

/**
 * The boxes you see on the homepage with icons of language and software.
 */
@Composable
fun SoftwareAndSkills() {
    // These are the boxes we want to create.
    val data = getToolBoxAreas()

    H1 {
        Row(
            Modifier.flexWrap(FlexWrap.Wrap),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SpanText("I Work(ed) With...", modifier = Modifier.fontFamily(FontHandler.getFont("h1")))
            Image(
                src = Res.AnimatedEmojis.MAN_LIFTING_WEIGHTS,
                modifier = Modifier.width(48.px).align(Alignment.CenterVertically)
            )
        }
    }

    SimpleGrid(numColumns(1, 1, 2, 2, 2)) {
        data.forEach { createGlassBoxContainer(it) }
    }
}

@Composable
fun createGlassBoxContainer(area: ToolsBoxArea) {
    val logger = Logger.get("createGlassBoxContainer")

    val ctx = rememberPageContext()

    logger.info("Creating Box Container for: ${area.name}")

    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                area.icon()
                SpanText(area.name, modifier = Modifier.fontFamily(FontHandler.getFont("h1")).padding(left = 4.px))
            }

            Row {
                GlassBox(modifier = Modifier.margin(all = 2.cssRem)) {
                    fun getAmountInGrid(): Int {
                        logger.trace("InnerWidth: ${window.innerWidth}")
                        logger.trace("OuterWidth: ${window.outerWidth}")

                        val needsTwoItemsWidth = 366
                        val needsOneItemsWidth = 281

                        if (window.innerWidth < needsOneItemsWidth) return 1
                        return if (window.innerWidth < needsTwoItemsWidth) 2 else 3
                    }

                    SimpleGrid(
                        modifier = Modifier.padding(0.5.cssRem),
                        numColumns = numColumns(base = getAmountInGrid(), sm = 3, md = 3, lg = 4)
                    ) {
                        for ((url, image) in area.imageLinks) {
                            logger.trace("\tCreating image for $image")

                            Box(modifier = Modifier.size(65.px).margin(all = 0.5.cssRem), contentAlignment = Alignment.Center) {
                                IconButtonWithHover(onClick = { }) {
                                    AppearanceAwareImage(
                                        src = image,
                                        noChange = true,
                                        modifier = Modifier.size(42.px).title(url).onClick { ctx.router.navigateTo(url) }.then(
                                            scaleOnHoverAnimation.toModifier()),
                                        dropShadow = true
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private val toolboxHeaderImageModifier = Modifier.width(24.px)

// USER TODO: Add your own boxes in this method if you want more than the 4 in the template.
@Composable
private fun getToolBoxAreas(): List<ToolsBoxArea> = listOf(
    ToolsBoxArea(
        "Programming Languages",
        PROGRAMMING_LANGUAGES
    ) { Image(src = Res.AnimatedEmojis.SMILING_FACE_WITH_SUNGLASSES, toolboxHeaderImageModifier) },
    ToolsBoxArea("IDEs", IDES) { Image(src = Res.AnimatedEmojis.DOTTED_LINE_FACE, toolboxHeaderImageModifier) },
    ToolsBoxArea("Software", SOFTWARE) { Image(src = Res.AnimatedEmojis.NERD_FACE, toolboxHeaderImageModifier) },
    ToolsBoxArea("Frameworks", FRAMEWORKS) { Image(src = Res.AnimatedEmojis.MAN_JUGGLING, toolboxHeaderImageModifier) },
)

// USER TODO: Put All IDEs you use here.
private val IDES: Collection<LinkableImage> = listOf(
    LinkableImage("https://www.jetbrains.com/fleet/", Res.Software.FLEET_LOGO),
    LinkableImage("https://www.jetbrains.com/clion/", Res.Software.CLION_LOGO),
    LinkableImage("https://www.jetbrains.com/resharper-cpp/", Res.Software.RESHARPER_CPP_LOGO),
    LinkableImage("https://www.jetbrains.com/resharper/", Res.Software.RESHARPER_CSHARP_LOGO),
    LinkableImage("https://www.jetbrains.com/idea/", Res.Software.INTELLIJ_LOGO),
    LinkableImage("https://www.jetbrains.com/go/", Res.Software.GOLAND_LOGO),
    LinkableImage("https://www.jetbrains.com/rust/", Res.Software.RUSTROVER_LOGO),
    LinkableImage("https://www.jetbrains.com/ruby/", Res.Software.RUBYMINE_LOGO),
)

// USER TODO: Put All Notable frameworks you use here.
private val FRAMEWORKS: Collection<LinkableImage> = listOf(
    LinkableImage("https://kobweb.varabyte.com/", Res.Frameworks.KOBWEB_LOGO),
    LinkableImage("https://www.raylib.com/", Res.Frameworks.RAYLIB_LOGO),
    LinkableImage("https://junit.org/junit5/", Res.Frameworks.JUNIT5_LOGO),
    LinkableImage("https://square.github.io/kotlinpoet/", Res.Frameworks.KOTLIN_POET_LOGO),
    LinkableImage("https://www.glfw.org/", Res.Frameworks.GLFW_LOGO),
    LinkableImage("https://github.com/sheredom/utest.h", Res.Frameworks.UTEST_LOGO),
    LinkableImage("https://opencv.org/", Res.Frameworks.OPENCV_LOGO),
    LinkableImage("https://github.com/kordlib/kord", Res.Frameworks.KORD_LOGO),
)

// USER TODO: Put All Other (relevant) software you use here.
private val SOFTWARE: Collection<LinkableImage> = listOf(
    LinkableImage("https://www.jetbrains.com/toolbox-app/", Res.Software.TOOLBOX_LOGO),
    LinkableImage("https://f-droid.org/", Res.Software.FDROID_LOGO),
    LinkableImage("https://www.apachefriends.org/", Res.Software.XAMPP_LOGO),
    LinkableImage("https://www.postman.com/", Res.Software.POSTMAN_LOGO),
    LinkableImage("https://www.jetbrains.com/datalore/", Res.Software.DATALORE_LOGO),
    LinkableImage("https://www.jetbrains.com/teamcity/", Res.Software.TEAMCITY_LOGO),
    LinkableImage("https://www.jetbrains.com/youtrack/", Res.Software.YOUTRACK_LOGO),
    LinkableImage("https://www.jetbrains.com/qodana/", Res.Software.QODANA_LOGO),
)

// USER TODO: Put All Programming language you are proficient in here.
private val PROGRAMMING_LANGUAGES: Collection<LinkableImage> = listOf(
    LinkableImage("https://en.wikipedia.org/wiki/C_(programming_language)", Res.Languages.C_LOGO),
    LinkableImage("https://en.wikipedia.org/wiki/C%2B%2B", Res.Languages.CPP_LOGO),
    LinkableImage("https://dotnet.microsoft.com/en-us/languages/csharp", Res.Languages.CSHARP_LOGO),
    LinkableImage("https://developer.mozilla.org/en-US/docs/Web/JavaScript", Res.Languages.JAVASCRIPT_LOGO),
    LinkableImage("https://dev.java/", Res.Languages.JAVA_LOGO),
    LinkableImage("https://kotlinlang.org/", Res.Languages.KOTLIN_LOGO),
    LinkableImage("https://www.python.org/", Res.Languages.PYTHON_LOGO),
    LinkableImage("https://fantom.org/", Res.Languages.FANTOM_LOGO),
)

data class ToolsBoxArea(
    val name: String,
    val imageLinks: Iterable<LinkableImage> = mutableListOf(),
    val icon: @Composable () -> Unit
)

data class LinkableImage(val url: String, val imageSrc: String)