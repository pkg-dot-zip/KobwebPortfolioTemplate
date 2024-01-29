package io.github.username.projectname.components.sections

import AppearanceAwareImage
import IconButtonNoHover
import androidx.compose.runtime.*
import io.github.username.projectname.Config
import io.github.username.projectname.Consts
import io.github.username.projectname.Res
import io.github.username.projectname.util.FontHandler
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.icons.fa.FaInbox
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent


// Edited, but original from: https://github.com/binayshaw7777/Kotfolio

@Composable
fun FooterSection(
    modifier: Modifier = Modifier.fillMaxWidth().gridRow(2),
    breakpoint: Breakpoint = rememberBreakpoint()
) {

    val footerColor = when (ColorMode.current) {
        ColorMode.LIGHT -> Res.Color.FOOTER_COLOR_LIGHT
        ColorMode.DARK -> Res.Color.FOOTER_COLOR_DARK
    }

    var isHorizontal by remember { mutableStateOf(true) }

    isHorizontal = breakpoint >= Breakpoint.MD

    val footerColumnAlignment = if (isHorizontal) Alignment.Start else Alignment.CenterHorizontally

    FooterContent(breakpoint, footerColumnAlignment, footerColor, modifier)
}

@Composable
private fun FooterContent(
    breakpoint: Breakpoint,
    footerColumnAlignment: Alignment.Horizontal,
    footerColor: Color.Rgb,
    modifier: Modifier
) {
    val ctx = rememberPageContext()

    Box(
        modifier = Modifier.padding(
            topBottom = 1.5.cssRem, leftRight = 10.percent
        ).backgroundColor(footerColor).fontFamily(FontHandler.getFont("footer"))
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().margin(top = 2.cssRem),
            horizontalAlignment = footerColumnAlignment
        ) {

            if (breakpoint >= Breakpoint.MD) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(topBottom = 2.cssRem),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        addEmailButton(ctx)
                    }
                    Spacer()
                    addSocialIconButtons(ctx)
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(topBottom = 2.cssRem),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        addEmailButton(ctx)
                    }
                    Spacer()
                    addSocialIconButtons(ctx, Modifier.margin(top = 2.cssRem))
                }
            }
        }
    }
}

@Composable
private fun addEmailButton(ctx: PageContext) {
    Button(
        onClick = {
            ctx.router.navigateTo(Consts.MAIL_TO)
        },
        size = ButtonSize.MD,
    ) {
        FaInbox(Modifier.margin(right = 0.5.cssRem), IconSize.SM)
        SpanText(
            text = "Email",
            modifier = Modifier.fontFamily(FontHandler.getFont("footer"))
        )
    }
}

@Composable
private fun addSocialIconButtons(ctx: PageContext, modifier: Modifier = Modifier) {
    SimpleGrid(
        modifier = modifier,
        numColumns = numColumns(base = 3, sm = 3, lg = 5)
    ) {
        for (social in Config.Socials.getSocials()) {
            IconButtonNoHover(onClick = { ctx.router.navigateTo(social.key) }) {
                AppearanceAwareImage(src = social.value)
            }
        }
    }
}