package io.github.username.projectname.pages

import androidx.compose.runtime.Composable
import io.github.username.projectname.components.sections.AllReposSection
import io.github.username.projectname.components.sections.FooterSection
import io.github.username.projectname.components.sections.HeaderSection
import io.github.username.projectname.components.widgets.ThemeChangeButton
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page

@Page
@Composable
fun Repositories() {
    ThemeChangeButton()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Header.
        HeaderSection()

        // Site contents.
        AllReposSection()

        // Footer.
        FooterSection()
    }
}