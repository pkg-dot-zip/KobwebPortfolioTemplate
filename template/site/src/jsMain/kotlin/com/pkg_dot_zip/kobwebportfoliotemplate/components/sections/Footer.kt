package com.pkg_dot_zip.kobwebportfoliotemplate.components.sections

import AppearanceAwareImage
import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.Res
import com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets.IconButtonWithHover
import com.pkg_dot_zip.kobwebportfoliotemplate.toSitePalette
import com.pkg_dot_zip.kobwebportfoliotemplate.util.date.SpecialDateHandler
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Span

val FooterStyle = CssStyle.base {
    Modifier
        .backgroundColor(colorMode.toSitePalette().nearBackground)
        .padding(topBottom = 1.5.cssRem, leftRight = 10.percent)
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Box(FooterStyle.toModifier().then(modifier), contentAlignment = Alignment.Center) {
        SimpleGrid(numColumns(1), Modifier.align(Alignment.Center)) {

            // This is the text you see whenever a special date emoji is displayed! See SpecialDateHandler.
            val specialText =
                SpecialDateHandler.getValueOnSpecialDate { it.specialDateText }
            if (specialText != null) {
                SpanText(specialText, Modifier.textAlign(TextAlign.Center).fontSize(16.px).fontStyle(FontStyle.Italic))
                Box(Modifier.margin(topBottom = 8.px)) { }
            }

            // This the template text. Don't remove this please.
            Span(Modifier.textAlign(TextAlign.Center).toAttrs()) {
                val sitePalette = ColorMode.current.toSitePalette()
                SpanText("Built with ")
                Link(
                    "https://github.com/varabyte/kobweb",
                    "Kobweb",
                    Modifier.setVariable(ColorVar, sitePalette.brand.primary),
                    variant = UncoloredLinkVariant
                )
                SpanText(", template designed by ")

                Link(
                    "https://github.com/pkg-dot-zip",
                    "pkg-dot-zip",
                    Modifier.setVariable(ColorVar, sitePalette.brand.accent).whiteSpace(WhiteSpace.NoWrap),
                    variant = UncoloredLinkVariant
                )
            }

            Box(Modifier.margin(topBottom = 8.px)) { }

            // This are the social icons in the footer.
            val ctx = rememberPageContext()
            Span(Modifier.textAlign(TextAlign.Center).toAttrs()) {
                for ((url, imageRes) in getFooterSocials()) {
                    IconButtonWithHover(onClick = { ctx.router.navigateTo(url) }) {
                        AppearanceAwareImage(src = imageRes)
                    }
                }

                // 😉. Feel free to remove if professional.
                IconButtonWithHover(onClick = { ctx.router.navigateTo("https://www.youtube.com/watch?v=dQw4w9WgXcQ") }) {
                    AppearanceAwareImage(
                        src = Res.AnimatedEmojis.FACE_IN_CLOUDS,
                        Modifier.width(42.px).height(42.px),
                        noChange = true
                    )
                }
            }
        }
    }
}

// USER TODO: Add your socials here!
/**
 * All the socials you see on the bottom of the page in the footer. Feel free to add other socials!
 */
private fun getFooterSocials(): Map<String, String> = mapOf(
    "https://github.com/varabyte/" to Res.Socials.GITHUB_LOGO,
    "https://www.linkedin.com/in/williamhgates/" to Res.Socials.LINKEDIN_LOGO,
)
