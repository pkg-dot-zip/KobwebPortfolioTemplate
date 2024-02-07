package io.github.username.projectname.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.icons.fa.IconStyle
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import io.github.username.projectname.Config
import io.github.username.projectname.components.styles.ButtonSizeAddons
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun ThemeChangeButton() {
    var colorMode by ColorMode.currentState

    val buttonSize: ButtonSize =
        when (val breakpoint = rememberBreakpoint()) {
            Breakpoint.ZERO -> ButtonSizeAddons.XLG
            Breakpoint.SM -> ButtonSizeAddons.XLG
            Breakpoint.MD -> ButtonSizeAddons.XLG
            Breakpoint.LG -> ButtonSizeAddons.XLG
            Breakpoint.XL -> ButtonSizeAddons.XLG
            else -> {
                println("Not familiar with ${breakpoint.name}. Help!")
                ButtonSize.MD
            }
        }

    val roundButtonModifier: Modifier =
        if (Config.IS_THEME_BUTTON_ROUND) Modifier.borderRadius(50.percent) else Modifier

    Button(
        onClick = { colorMode = colorMode.opposite },
        size = buttonSize,
        modifier = Modifier
            .then(roundButtonModifier)
            .padding(0.px)
            .position(Position.Fixed)
            .right(20.px)
            .bottom(20.px)
            .cursor(Cursor.Pointer)
    ) {
        if (colorMode.isLight) FaMoon(style = IconStyle.FILLED) else FaSun(style = IconStyle.FILLED)
    }
}