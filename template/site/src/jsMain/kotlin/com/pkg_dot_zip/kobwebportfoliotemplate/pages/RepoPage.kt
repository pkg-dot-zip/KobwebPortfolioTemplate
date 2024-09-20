package com.pkg_dot_zip.kobwebportfoliotemplate.pages

import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.Res
import com.pkg_dot_zip.kobwebportfoliotemplate.components.layouts.PageLayout
import com.pkg_dot_zip.kobwebportfoliotemplate.components.sections.CustomRepoCardSection
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.percent

@Page
@Composable
fun RepoPage() {
    PageLayout("Repositories") {

        Box(
            Modifier.displayIfAtLeast(Breakpoint.MD).align(Alignment.CenterHorizontally)
                .display(DisplayStyle.Flex)
                .justifyContent(JustifyContent.Center)
        ) {
            Image(Res.OCTOCAT, "Octocat", modifier = Modifier.width(25.percent))
        }

        Row(Modifier.flexWrap(FlexWrap.Wrap).align(Alignment.CenterHorizontally).justifyContent(JustifyContent.Center)) {
            SpanText(
                "This page is a window into my development work, showcasing a range of GitHub repositories that span different languages, frameworks, and ideas. Every project here tells a story of learning, experimenting, and pushing the boundaries of code. Whether you're a fellow developer or just curious, I invite you to explore these repositories and see what I've been up to!",
                modifier = Modifier.textAlign(TextAlign.Center)
            )
        }

        Row {
            CustomRepoCardSection()
        }
    }
}
