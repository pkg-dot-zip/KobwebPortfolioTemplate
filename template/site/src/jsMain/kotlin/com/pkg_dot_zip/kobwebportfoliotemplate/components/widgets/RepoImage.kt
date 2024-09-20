package com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets

import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.Res
import com.pkg_dot_zip.kobwebportfoliotemplate.util.FontHandler
import com.pkg_dot_zip.kobwebportfoliotemplate.util.extensions.toInt
import com.pkg_dot_zip.kobwebportfoliotemplate.util.repo.Repository
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*

enum class RepoImageFont(val fontString: String) {
    INTER(FontHandler.Font.INTER),
    BITTER(FontHandler.Font.BITTER),
    RALEWAY(FontHandler.Font.RALEWAY),
    ROKKITT(FontHandler.Font.ROKKITT),
    SOURCE_CODE_PRO("Source%20Code%20Pro"),
    KOHO("KoHo"),
    JOST(FontHandler.Font.JOST),
}

enum class RepoImagePattern(val patternString: String) {
    SIGNAL("Signal"),
    CHARLIE_BROWN("Charlie%20Brown"),
    FORMAL_INVITATION("Formal%20Invitation"),
    PLUS("Plus"),
    CIRCUIT_BOARD("Circuit%20Board"),
    OVERLAPPING_HEXAGONS("Overlapping%20Hexagons"),
    BRICK_WALL("Brick%20Wall"),
    FLOATING_COGS("Floating%20Cogs"),
    DIAGONAL_STRIPES("Diagonal%20Stripes"),
    SOLID("Solid"),
}

@Composable
fun RepoImage(
    context: PageContext,
    repository: Repository,
    showLanguage: Boolean = true,
    showOwner: Boolean = true,
    showName: Boolean = true,
    pattern: RepoImagePattern = RepoImagePattern.SOLID,
    showStars: Boolean = true,
    useLightTheme: Boolean = true,
    showForks: Boolean = false,
    showIssues: Boolean = false,
    showPullRequests: Boolean = false,
    showDescription: Boolean = true,
    font: RepoImageFont = RepoImageFont.INTER,

    roundedCornerAmount: CSSLengthOrPercentageNumericValue = 50.px,
    borderWidth: CSSLengthNumericValue = 2.px,
    borderStyle: LineStyle = LineStyle.Dashed,
    borderColor: CSSColorValue = when (ColorMode.current) {
        ColorMode.LIGHT -> Res.Color.SOFTWARE_AND_SKILLS_BOX_BORDER_COLOR_LIGHT
        ColorMode.DARK -> Res.Color.SOFTWARE_AND_SKILLS_BORDER_COLOR_DARK
    },
    blendMode: MixBlendMode = MixBlendMode.Normal,
) {
    var imageSource = "https://socialify.git.ci/${repository.full_name!!}/image?"
    imageSource += "language=${showLanguage.toInt()}"
    imageSource += "&owner=${showOwner.toInt()}"
    imageSource += "&name=${showName.toInt()}"
    imageSource += "&pattern=${pattern.patternString}"
    imageSource += "&stargazers=${showStars.toInt()}"
    imageSource += "&theme=${if (useLightTheme) "Light" else "Dark"}"
    imageSource += "&forks=${showForks.toInt()}"
    imageSource += "&issues=${showIssues.toInt()}"
    imageSource += "&pulls=${showPullRequests.toInt()}"
    imageSource += "&description=${showDescription.toInt()}"
    imageSource += "&font=${font.fontString}"

    Box(modifier = Modifier
        .margin(leftRight = 1.cssRem)
        .borderRadius(roundedCornerAmount)
        .border(width = borderWidth, style = borderStyle, color = borderColor)
        .styleModifier {
            mixBlendMode(blendMode)
        }
        .id("${repository.name}FullRepoDiv")
    ) {
        Image(
            imageSource,
            modifier = Modifier.onClick {
                context.router.navigateTo(repository.html_url!!)
            }
                .fillMaxWidth()
                .fillMaxHeight()
                .borderRadius(10.percent)
                .objectFit(ObjectFit.Cover)
                .align(Alignment.CenterStart) // This works. Now fix size.
                .cursor(Cursor.Pointer)
                .id("${repository.name}Image")
        )
    }
}