package com.pkg_dot_zip.kobwebportfoliotemplate.components.sections

import androidx.compose.runtime.*
import com.pkg_dot_zip.kobwebportfoliotemplate.Res
import com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets.RepoImage
import com.pkg_dot_zip.kobwebportfoliotemplate.util.FontHandler
import com.pkg_dot_zip.kobwebportfoliotemplate.util.Logger
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.RepoHandler
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.Repository
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.RepositoryShowingMode
import com.varabyte.kobweb.compose.css.AlignContent
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.w3c.fetch.Request


@Composable
fun RepoSection() {
    Column(Modifier.alignContent(AlignContent.Center), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        H1 {
            Row(Modifier.flexWrap(FlexWrap.Wrap), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                SpanText("Popular Repositories ", modifier = Modifier.fontFamily(FontHandler.getFont("h1")))
                Image(src = Res.AnimatedEmojis.STAR_STRUCK, modifier = Modifier.width(48.px).align(Alignment.CenterVertically))
            }
        }

        addRepos()
    }

    // Only adds some margin below this section.
    Box(modifier = Modifier.margin(bottom = 10.px))
}

@Composable
private fun addRepos() {
    generateRepoUIElements()
}

@Composable
private fun generateRepoUIElements() {
    val logger = Logger.get("generateRepoUIElements")

    logger.info("Generating Repo UI elements... ⚙\uFE0F")

    for (repository in RepoHandler.getAllRepos(RepositoryShowingMode.MOST_STARRED)) {
        createUIElementForRepo(repository)
    }
}

@Composable
private fun createUIElementForRepo(repository: Repository) {
    Row(Modifier.flexWrap(FlexWrap.Wrap).margin(topBottom = 1.5.cssRem), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom) {
        FaGithub(
            modifier = Modifier.margin(right = 8.px).align(Alignment.CenterVertically),
            size = IconSize.XXL
        )
        Link(
            path = "${repository.html_url}",
            text = "${repository.name}",
            modifier = Modifier.fontFamily(FontHandler.getFont("repotext"))
        )
        SpanText(
            " - ",
            modifier = Modifier.fontFamily(FontHandler.getFont("repotext")).displayIfAtLeast(Breakpoint.SM)
        )
        SpanText(
            "${repository.description}",
            modifier = Modifier.fontFamily(FontHandler.getFont("repotext")).displayIfAtLeast(Breakpoint.SM)
        )
    }

    Row(Modifier.displayIfAtLeast(Breakpoint.SM)) {
        val colorMode by ColorMode.currentState
        RepoImage(
            rememberPageContext(), repository, useLightTheme = colorMode.isLight
        )
    }
}