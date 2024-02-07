package io.github.username.projectname.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import io.github.username.projectname.Config
import io.github.username.projectname.Res
import io.github.username.projectname.util.FontHandler
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.H1

@Composable
fun AboutMe(modifier: Modifier = Modifier) {

    Column(modifier) {
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            H1 {
                SpanText(
                    "About Me",
                    modifier = Modifier.fontFamily(FontHandler.getFont("h1"))
                )
            }
        }

        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                src = Res.Image.PERSONAL_PICTURE,
                width = 200,
                height = 200,
            )
        }

        Row(Modifier.align(Alignment.CenterHorizontally).maxWidth(50.percent)) {
            SpanText(
                Config.ABOUT_ME_TEXT,
                modifier = Modifier.fontFamily(FontHandler.getFont("text")).overflow(Overflow.Hidden).textAlign(
                    TextAlign.Center
                )
            )
        }
    }
}