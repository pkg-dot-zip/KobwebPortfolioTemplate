package com.pkg_dot_zip.kobwebportfoliotemplate.pages

import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.HeadlineTextStyle
import com.pkg_dot_zip.kobwebportfoliotemplate.SubheadlineTextStyle
import com.pkg_dot_zip.kobwebportfoliotemplate.components.layouts.PageLayout
import com.pkg_dot_zip.kobwebportfoliotemplate.components.sections.RepoSection
import com.pkg_dot_zip.kobwebportfoliotemplate.components.sections.SoftwareAndSkills
import com.pkg_dot_zip.kobwebportfoliotemplate.toSitePalette
import com.pkg_dot_zip.kobwebportfoliotemplate.util.date.EmojiBasedOnDate
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.HorizontalDivider
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.ColorSchemes
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

// Container that has a tagline and grid on desktop, and just the tagline on mobile
val HeroContainerStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(2.cssRem) }
    Breakpoint.MD { Modifier.margin { top(20.vh) } }
}

val HomeGridStyle = CssStyle.base {
    Modifier
        .gap(0.5.cssRem)
        .width(70.cssRem)
        .height(18.cssRem)
}

@Page
@Composable
fun HomePage() {
    PageLayout("Home") {
        Row(HeroContainerStyle.toModifier()) {
            Box {
                val sitePalette = ColorMode.current.toSitePalette()

                Column(Modifier.gap(2.cssRem)) {
                    Div(HeadlineTextStyle.toAttrs()) {
                        SpanText(
                            "Welcome to the website of ", Modifier.color(
                                when (ColorMode.current) {
                                    ColorMode.LIGHT -> Colors.Black
                                    ColorMode.DARK -> Colors.White
                                }
                            )
                        )
                        SpanText(
                            "your name", // USER TODO: Add your name here!
                            Modifier
                                .color(sitePalette.brand.accent)
                                // Use a shadow so this light-colored word is more visible in light mode
                                .textShadow(0.px, 0.px, blurRadius = 0.5.cssRem, color = Colors.Gray)
                        )
                    }

                    Div(SubheadlineTextStyle.toAttrs()) {
                        SpanText("You can read the ")
                        Link("/about", "About")
                        SpanText(" page for more information about me.")
                    }

                    Div(SubheadlineTextStyle.toAttrs()) {
                        SpanText("Check out the  ")
                        Link("/repo-page", "Repositories")
                        SpanText(" to see what I'm working on.")
                    }

                    val ctx = rememberPageContext()
                    Button(modifier = Modifier.fillMaxWidth(), onClick = {
                        ctx.router.tryRoutingTo("/about")
                    }, colorScheme = ColorSchemes.Blue) {
                        Text("Read About Me")
                    }
                }
            }

            Div(
                HomeGridStyle
                .toModifier()
                .align(Alignment.CenterVertically)
                .displayIfAtLeast(Breakpoint.MD)
                .display(DisplayStyle.Flex)
                .justifyContent(JustifyContent.Center)
                .toAttrs()
            ) {
                Image(src = EmojiBasedOnDate.getEmojiRandomly(), description = "Astonished Face")
            }
        }

        HorizontalDivider()
        SoftwareAndSkills()
        HorizontalDivider()
        RepoSection()
    }
}
