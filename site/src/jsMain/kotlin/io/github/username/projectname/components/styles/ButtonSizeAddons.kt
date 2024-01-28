package io.github.username.projectname.components.styles

import com.varabyte.kobweb.silk.components.forms.ButtonSize
import org.jetbrains.compose.web.css.cssRem

object ButtonSizeAddons {
    object XLG : ButtonSize {
        override val fontSize = 1.25.cssRem
        override val height = 3.5.cssRem
        override val horizontalPadding = 2.cssRem
    }
}