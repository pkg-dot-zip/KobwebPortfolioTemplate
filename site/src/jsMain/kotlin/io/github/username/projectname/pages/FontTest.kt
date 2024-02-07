package io.github.username.projectname.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.Page
import io.github.username.projectname.components.sections.FontTestSection
import io.github.username.projectname.components.sections.FooterSection
import io.github.username.projectname.components.sections.HeaderSection
import io.github.username.projectname.components.widgets.ThemeChangeButton

@Page
@Composable
fun FontTest() {
    ThemeChangeButton()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderSection()

        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                FontTestSection()
            }
        }

        FooterSection()
    }
}