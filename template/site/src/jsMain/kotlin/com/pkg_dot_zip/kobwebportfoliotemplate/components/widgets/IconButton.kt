package com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets

import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.CircleButtonVariant
import com.pkg_dot_zip.kobwebportfoliotemplate.UncoloredButtonVariant
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.s

@Composable
fun IconButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Button(
        onClick = { onClick() },
        Modifier.setVariable(ButtonVars.FontSize, 1.em), // Make button icon size relative to parent container font size.
        variant = CircleButtonVariant.then(UncoloredButtonVariant)
    ) {
        content()
    }
}

/**
 * A nice zoom animation upon hovering on icons.
 */
val scaleOnHoverAnimation = CssStyle {
    val scale = 1.1
    val duration = 0.175.s
    val timingFunction = AnimationTimingFunction.Linear

    hover {
        Modifier.scale(scale).transition(Transition.of("all", duration, timingFunction))
    }
}

@Composable
fun IconButtonWithHover(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = { onClick() },
        Modifier
            .backgroundColor(Colors.Transparent)
            .then(scaleOnHoverAnimation.toModifier()),
    ) {
        content()
    }
}