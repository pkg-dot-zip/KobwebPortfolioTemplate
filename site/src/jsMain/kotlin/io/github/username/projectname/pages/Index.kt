package io.github.username.projectname.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.style.breakpoint.ResponsiveValues
import io.github.username.projectname.Config
import io.github.username.projectname.components.sections.*
import io.github.username.projectname.components.widgets.HorizontalRuler
import io.github.username.projectname.components.widgets.ThemeChangeButton

@Page
@Composable
fun HomePage() {
    ThemeChangeButton()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Header.
        HeaderSection()

        // Site contents.
        val showVideo = Config.Video.SHOW_VIDEO
        SimpleGrid(
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .fillMaxHeight().fillMaxWidth(), // TODO: Is this necessary?
            numColumns = ResponsiveValues(
                base = if (showVideo) 1 else 1,
                sm = 1,
                md = 1,
                lg = if (showVideo) 2 else 1,
                xl = if (showVideo) 2 else 1
            )
        ) {
            if (showVideo) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    VideoSection()
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AboutMe()
            }
        }

        HorizontalRuler()
        SoftwareAndSkills()
        HorizontalRuler()
        RepoSection()

        // Footer.
        FooterSection()
    }
}