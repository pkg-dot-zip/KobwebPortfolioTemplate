package io.github.username.projectname.components.sections

import androidx.compose.runtime.Composable
import io.github.username.projectname.Config
import io.github.username.projectname.components.widgets.VideoComponent
import io.github.username.projectname.util.FontHandler
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.H1

@Composable
fun VideoSection(modifier: Modifier = Modifier) {
    Column(modifier) {
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            H1 {
                SpanText(
                    "Recommended Video",
                    modifier = Modifier.fontFamily(FontHandler.getFont("h1"))
                )
            }
        }

        Row(Modifier.align(Alignment.CenterHorizontally)) {
            VideoComponent(Config.Video.VIDEO_EMBED_URL)
        }
    }
}

