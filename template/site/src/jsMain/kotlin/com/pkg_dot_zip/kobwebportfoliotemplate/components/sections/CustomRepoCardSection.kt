package com.pkg_dot_zip.kobwebportfoliotemplate.components.sections

import androidx.compose.runtime.*
import com.pkg_dot_zip.kobwebportfoliotemplate.Res
import com.pkg_dot_zip.kobwebportfoliotemplate.util.FontHandler
import com.pkg_dot_zip.kobwebportfoliotemplate.util.Logger
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.RepoHandler
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.Repository
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import com.varabyte.kobweb.compose.css.MixBlendMode
import com.varabyte.kobweb.compose.css.mixBlendMode
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.icons.fa.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.*
import org.w3c.fetch.Request

@Composable
fun CustomRepoCardSection() {
    var data by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        data = window.fetch(Request(RepoHandler.API_URL)).await().text().await()
    }

    if (data != null) generateAllRepoElements(data!!)
}

@Composable
private fun generateAllRepoElements(data: String) {
    val logger = Logger.get("generateAllRepoElements")

    SimpleGrid(numColumns(base = 1, sm = 1, md = 2, lg = 2)) {
        for (repository in RepoHandler.getRepoList(data, RepoHandler.RepositoryShowingMode.ALL)) {
            logger.info("Creating UI Element for repo: ${repository.name}")
            createUIElementForRepo(repository)
        }
    }
}

@Composable
private fun createUIElementForRepo(
    repository: Repository,
    roundedCornerAmount: CSSLengthOrPercentageNumericValue = 50.px,
    borderWidth: CSSLengthNumericValue = 2.px,
    borderStyle: LineStyle = LineStyle.Dashed,
    borderColor: CSSColorValue = when (ColorMode.current) {
        ColorMode.LIGHT -> Res.Color.SOFTWARE_AND_SKILLS_BOX_BORDER_COLOR_LIGHT
        ColorMode.DARK -> Res.Color.SOFTWARE_AND_SKILLS_BORDER_COLOR_DARK
    },
    blendMode: MixBlendMode = MixBlendMode.Normal,
) {
    Box(modifier = Modifier
        .margin(leftRight = 1.cssRem, topBottom = 2.cssRem)
        .borderRadius(roundedCornerAmount)
        .border(width = borderWidth, style = borderStyle, color = borderColor)
        .styleModifier {
            mixBlendMode(blendMode)
        }.id("${repository.name}RepoDiv")
    ) {
        Column {
            // Text above Repo Image.
            Row(modifier = Modifier.margin(all = 2.cssRem)) {
                FaGithub(
                    modifier = Modifier.align(Alignment.CenterVertically).margin(right = 8.px),
                    size = IconSize.XXL
                )
                Link(
                    path = "${repository.html_url}",
                    text = "${repository.name}",
                    modifier = Modifier.fontFamily(FontHandler.getFont("repotext")).fontSize(90.percent)
                )
            }

            Row(modifier = Modifier.margin(all = 2.cssRem)) {
                SpanText("${repository.description}", modifier = Modifier.fontFamily(FontHandler.getFont("repotext")))
            }
        }

        // Top right information about stargazers and watchers.
        Column(Modifier.align(Alignment.TopEnd)) {
            Row(modifier = Modifier.margin(all = 2.cssRem)) {
                FaStar(
                    modifier = Modifier.align(Alignment.CenterVertically).margin(right = 8.px),
                    size = IconSize.LG,
                    style = IconStyle.FILLED
                )
                SpanText(
                    text = "${repository.stargazers_count}",
                    modifier = Modifier.fontFamily(FontHandler.getFont("repostarwatch"))
                )

                Box(Modifier.margin(leftRight = 4.px))

                FaEye(
                    modifier = Modifier.align(Alignment.CenterVertically).margin(right = 8.px),
                    size = IconSize.LG,
                    style = IconStyle.FILLED
                )
                SpanText(
                    text = "${repository.watchers_count}",
                    modifier = Modifier.fontFamily(FontHandler.getFont("repostarwatch"))
                )
            }
        }
    }
}