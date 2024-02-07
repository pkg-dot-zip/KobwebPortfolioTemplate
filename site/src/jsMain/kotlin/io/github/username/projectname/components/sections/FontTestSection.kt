package io.github.username.projectname.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.silk.components.text.SpanText
import io.github.username.projectname.Config
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun FontTestSection() {
    Column {
        H1 { Text("FONT TEST") }

        for (font in Config.Fonts.TEST_FONTS) {
            println("Creating Font element for $font")

            Column(Modifier.margin(topBottom = 2.cssRem)) {
                Row {
                    SpanText("Font: ")
                    SpanText(font, Modifier.fontFamily(font).fontStyle(FontStyle.Italic))
                }
                SpanText(
                    text = "Whereas recognition of the inherent dignity",
                    modifier = Modifier.fontFamily(font).fontSize(FontSize.XXLarge)
                )
            }
        }
    }
}