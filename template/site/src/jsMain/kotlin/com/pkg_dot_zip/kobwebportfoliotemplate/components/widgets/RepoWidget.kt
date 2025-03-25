package com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets

import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.Res
import com.pkg_dot_zip.kobwebportfoliotemplate.util.FontHandler
import com.pkg_dot_zip.kobwebportfoliotemplate.util.ProjectPageHandler
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.Repository
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.*
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.JustifyContent

@Composable
fun RepoWidget(
    repository: Repository,
    borderColor: CSSColorValue = when (ColorMode.current) {
        ColorMode.LIGHT -> Res.Color.SOFTWARE_AND_SKILLS_BOX_BORDER_COLOR_LIGHT
        ColorMode.DARK -> Res.Color.SOFTWARE_AND_SKILLS_BORDER_COLOR_DARK
    },
) {
    Box(modifier = Modifier
        .margin(leftRight = 1.cssRem, topBottom = 2.cssRem)
        .borderRadius(5.px)
        .border(width = 1.px, style = LineStyle.Solid, color = borderColor)
        .styleModifier {
            mixBlendMode(MixBlendMode.Normal)
        }.id("${repository.name}RepoDiv")
        .padding(1.cssRem)
    ) {
        Column(Modifier.width(100.percent)) {
            // Text above Repo Image.
            Row(modifier = Modifier.width(100.percent).margin(bottom = 1.cssRem).justifyContent(JustifyContent.SpaceBetween).alignItems(AlignItems.Center)) {
                FaGithub(
                    modifier = Modifier.margin(right = 8.px),
                    size = IconSize.XXL
                )
                Link(
                    path = "${repository.html_url}",
                    text = "${repository.name}",
                    modifier = Modifier.fontFamily(FontHandler.getFont("repotext")).fontSize(90.percent).textOverflow(
                        TextOverflow.Ellipsis).overflow(Overflow.Hidden).flexGrow(1)
                )

                Row(Modifier.alignItems(AlignItems.Center).displayIfAtLeast(Breakpoint.SM)) {
                    FaStar(
                        modifier = Modifier.margin(right = 8.px),
                        size = IconSize.LG,
                        style = IconStyle.FILLED
                    )
                    SpanText(
                        text = "${repository.stargazers_count}",
                        modifier = Modifier.fontFamily(FontHandler.getFont("repostarwatch"))
                    )
                }
            }

            SpanText(repository.description!!, modifier = Modifier.fontFamily(FontHandler.getFont("repotext")).textAlign(
                TextAlign.Center))

            // Bottom links.
            val ctx = rememberPageContext()
            if (ProjectPageHandler.repoHasProjectPage(ctx, repository)) {
                Link(modifier = Modifier.margin(leftRight = 2.cssRem, top = 0.5.cssRem).align(Alignment.CenterHorizontally), path = ProjectPageHandler.projectPagePathString(repository), text = "Visit Blog Post")
            }
        }
    }
}