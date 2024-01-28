package io.github.username.projectname.components.widgets

import androidx.compose.runtime.Composable
import io.github.username.projectname.Config
import io.github.username.projectname.Res
import io.github.username.projectname.util.extensions.toInt
import io.github.username.projectname.util.github.Repository
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px

@Composable
fun RepoImageSimple(
    context: PageContext,
    repository: Repository,
) {
    Box(Modifier.id("${repository.name}SimpleRepoDiv")) {
        Image("https://gh-card.dev/repos/${repository.full_name!!}.svg", modifier = Modifier.onClick {
            context.router.navigateTo(repository.html_url!!)
        }.cursor(Cursor.Pointer))
    }
}

enum class RepoImageFont(val fontString: String) {
    INTER(Res.Font.INTER),
    BITTER(Res.Font.BITTER),
    RALEWAY(Res.Font.RALEWAY),
    ROKKITT(Res.Font.ROKKITT),
    SOURCE_CODE_PRO("Source%20Code%20Pro"),
    KOHO("KoHo"),
    JOST(Res.Font.JOST),
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
fun RepoImageFull(
    context: PageContext,
    repository: Repository,
    showLanguage: Boolean = Config.Repositories.REPO_IMAGE_SHOW_LANGUAGE,
    showOwner: Boolean = Config.Repositories.REPO_IMAGE_SHOW_OWNER,
    showName: Boolean = Config.Repositories.REPO_IMAGE_SHOW_NAME,
    pattern: RepoImagePattern = Config.Repositories.REPO_IMAGE_PATTERN,
    showStars: Boolean = Config.Repositories.REPO_IMAGE_SHOW_STARS,
    useLightTheme: Boolean = true,
    showForks: Boolean = Config.Repositories.REPO_IMAGE_SHOW_FORKS,
    showIssues: Boolean = Config.Repositories.REPO_IMAGE_SHOW_ISSUES,
    showPullRequests: Boolean = Config.Repositories.REPO_IMAGE_SHOW_PULL_REQUESTS,
    showDescription: Boolean = Config.Repositories.REPO_IMAGE_SHOW_DESCRIPTION,
    font: RepoImageFont = Config.Repositories.REPO_IMAGE_FONT,

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
                .align(Alignment.CenterStart) // This works. Now fix size.
                .cursor(Cursor.Pointer)
                .id("${repository.name}Image")
        )
    }
}