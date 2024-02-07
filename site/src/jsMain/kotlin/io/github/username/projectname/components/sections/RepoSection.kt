package io.github.username.projectname.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignContent
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import io.github.username.projectname.Config
import io.github.username.projectname.components.widgets.RepoImageFull
import io.github.username.projectname.util.FontHandler
import io.github.username.projectname.util.github.RepoHandler
import io.github.username.projectname.util.github.Repository
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.AlignContent
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.w3c.fetch.Request


@Composable
fun RepoSection() {
    H1 { SpanText("Repositories", modifier = Modifier.fontFamily(FontHandler.getFont("h1"))) }

    Column {
        addRepos()
    }

    // Only adds some margin below this section.
    Box(modifier = Modifier.margin(bottom = 10.px)) { }
}

@Composable
private fun addRepos() {
    var data by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        data = window.fetch(Request(RepoHandler.API_URL)).await().text().await()
    }

    if (data != null) generateRepoUIElements(data!!)
}

@Composable
private fun generateRepoUIElements(data: String) {
    println("Generating Repo UI elements... ⚙\uFE0F")


    for (repository in RepoHandler.getRepoList(data, Config.Repositories.REPOSITORY_SHOWING_MODE)) {
        createUIElementForRepo(repository)
    }
}

@Composable
private fun createUIElementForRepo(repository: Repository) {
    val breakpoint = rememberBreakpoint()

    Column {
        // Text above Repo Image.
        Row(
            modifier = Modifier.margin(all = 2.cssRem).align(Alignment.CenterHorizontally)
                .alignContent(AlignContent.Center)
        ) {
            if (breakpoint == Breakpoint.ZERO) {
                Column {
                    Row(Modifier.margin(topBottom = 1.5.cssRem)) {
                        FaGithub(
                            modifier = Modifier.align(Alignment.CenterVertically).margin(right = 8.px),
                            size = IconSize.XXL
                        )
                        Link(
                            path = "${repository.html_url}",
                            text = "${repository.name}",
                            modifier = Modifier.fontFamily(FontHandler.getFont("repotext"))
                        )
                    }

                    Row {
                        SpanText(
                            "${repository.description}",
                            modifier = Modifier.fontFamily(FontHandler.getFont("repotext"))
                        )
                    }
                }
            } else {
                FaGithub(
                    modifier = Modifier.align(Alignment.CenterVertically).margin(right = 8.px),
                    size = IconSize.XXL
                )
                Link(
                    path = "${repository.html_url}",
                    text = "${repository.name}",
                    modifier = Modifier.fontFamily(FontHandler.getFont("repotext"))
                )
                SpanText(" - ", modifier = Modifier.fontFamily(FontHandler.getFont("repotext")))
                SpanText("${repository.description}", modifier = Modifier.fontFamily(FontHandler.getFont("repotext")))
            }

        }

        Row(modifier = Modifier) {
            // Repo Image.
            val colorMode by ColorMode.currentState
            RepoImageFull(
                rememberPageContext(), repository, useLightTheme = colorMode.isLight
            )
        }
    }
}