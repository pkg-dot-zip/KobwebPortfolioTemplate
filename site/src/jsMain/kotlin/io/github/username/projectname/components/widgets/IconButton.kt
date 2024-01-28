import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.hover
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.css.em

// STOLEN FROM: https://github.com/binayshaw7777/Kotfolio

@Composable
fun IconButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = { onClick() },
        Modifier.setVariable(ButtonVars.FontSize, 1.em), // Make button icon size relative to parent container font size
    ) {
        content()
    }
}


val FooterIconsStyle by ComponentStyle {
    hover {
        Modifier.scale(1.1)
    }
}

@Composable
fun IconButtonNoHover(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = { onClick() },
        FooterIconsStyle.toModifier().setVariable(ButtonVars.FontSize, 1.em)
            .backgroundColor(Colors.Transparent), // Make button icon size relative to parent container font size
    ) {
        content()
    }
}