package com.pkg_dot_zip.kobwebportfoliotemplate.components.layouts

import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets.BlogPostHeaderWidget
import com.pkg_dot_zip.kobwebportfoliotemplate.toSitePalette
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

private val defaultHeaderModifier =
    Modifier.overflowWrap(OverflowWrap.BreakWord)

val BlogPostStyle = CssStyle {
    base { Modifier.fillMaxSize() }

    cssRule("h1") {
        Modifier
            .fontSize(3.cssRem)
            .fontWeight(400)
            .margin(bottom = 0.cssRem)
            .lineHeight(1.2) //1.5x doesn't look as good on very large text
            .then(defaultHeaderModifier)
    }

    cssRule("h2") {
        Modifier
            .fontSize(3.cssRem)
            .fontWeight(300)
            .margin(bottom = 0.cssRem)
            .then(defaultHeaderModifier)
    }

    cssRule("h3") {
        Modifier
            .fontSize(2.4.cssRem)
            .fontWeight(300)
            .margin(bottom = 0.cssRem)
            .then(defaultHeaderModifier)
    }

    cssRule("h4") {
        Modifier
            .fontSize(1.2.cssRem)
            .fontWeight(FontWeight.Bolder)
            .margin(bottom = 0.cssRem)
            .then(defaultHeaderModifier)
    }

    cssRule("h5") {
        Modifier
            .fontSize(1.cssRem)
            .fontWeight(FontWeight.Lighter)
            .margin(bottom = 0.cssRem)
            .then(defaultHeaderModifier)
    }

    cssRule("h6") {
        Modifier
            .fontSize(0.8.cssRem)
            .fontWeight(FontWeight.Light)
            .margin(bottom = 0.2.cssRem)
            .then(defaultHeaderModifier)
    }

    cssRule("ul") {
        Modifier.fillMaxWidth().overflowWrap(OverflowWrap.BreakWord)
    }

    cssRule(" :is(li,ol,ul)") {
        Modifier.margin(bottom = 0.25.cssRem)
    }

    cssRule("code") {
        Modifier
            .color(colorMode.toPalette().color.toRgb().copyf(alpha = 0.8f))
            .fontWeight(FontWeight.Bolder)
    }

    cssRule("pre") {
        Modifier
            .margin(top = 0.5.cssRem, bottom = 2.cssRem)
            .fillMaxWidth()
    }
    cssRule("pre > code") {
        Modifier
            .display(DisplayStyle.Block)
            .fillMaxWidth()
            .backgroundColor(colorMode.toSitePalette().nearBackground)
            .border(1.px, LineStyle.Solid, colorMode.toPalette().color)
            .borderRadius(0.25.cssRem)
            .padding(0.5.cssRem)
            .fontSize(1.cssRem)
            .overflow { x(Overflow.Auto) }
    }

    cssRule("img") {
        Modifier.fillMaxWidth()
    }
}

@Composable
fun BlogPostLayout(title: String, content: @Composable () -> Unit) {
    PageLayout(title) {
        Div(BlogPostStyle.toAttrs()) {
            BlogPostHeaderWidget()
            content()
        }
    }
}
