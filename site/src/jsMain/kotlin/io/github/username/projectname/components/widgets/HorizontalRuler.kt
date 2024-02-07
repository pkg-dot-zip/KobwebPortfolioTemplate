package io.github.username.projectname.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import io.github.username.projectname.Res
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Hr

@Composable
fun HorizontalRuler(
    fillAmount: CSSLengthOrPercentageNumericValue = 90.percent,
    marginTopBottom: CSSLengthNumericValue = 2.cssRem,
    borderWidth: CSSLengthNumericValue = 1.px,
    borderStyle: LineStyle = LineStyle.Dotted,
    borderColor: CSSColorValue = when (ColorMode.current) {
        ColorMode.LIGHT -> Res.Color.HORIZONTAL_RULER_COLOR_LIGHT
        ColorMode.DARK -> Res.Color.HORIZONTAL_RULER_COLOR_DARK
    },
) {
    Hr(
        Modifier.fillMaxWidth(fillAmount).margin(topBottom = marginTopBottom)
            .border(width = borderWidth, style = borderStyle, color = borderColor).toAttrs()
    )
}