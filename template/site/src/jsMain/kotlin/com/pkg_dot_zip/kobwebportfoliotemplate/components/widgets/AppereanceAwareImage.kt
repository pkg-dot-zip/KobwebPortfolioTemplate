import androidx.compose.runtime.Composable
import com.pkg_dot_zip.kobwebportfoliotemplate.util.extensions.toInt
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.graphics.ImageKind
import com.varabyte.kobweb.silk.style.CssStyleVariant
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLImageElement

// FROM: https://github.com/binayshaw7777/Kotfolio. Show some love to Binay and make sure to check out his project!

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun AppearanceAwareImage(
    src: String,
    modifier: Modifier = Modifier,
    variant: CssStyleVariant<ImageKind>? = null,
    width: Int? = null,
    height: Int? = null,
    alt: String = "",
    autoPrefix: Boolean = true,
    ref: ElementRefScope<HTMLImageElement>? = null,
    noChange: Boolean = false,
    dropShadow: Boolean = false,
) {
    val isLight = when (ColorMode.current) {
        ColorMode.LIGHT -> true
        ColorMode.DARK -> false
    }

    Image(
        src = src,
        modifier = Modifier
            .styleModifier {
                filter {
                    if (!noChange) invert(isLight.toInt())
                    if (dropShadow) dropShadow(0.1.px, 0.1.px, 1.px, Color("#808080"))
                }
            }
            .then(modifier),
        variant = variant,
        width = width,
        height = height,
        alt = alt,
        autoPrefix = autoPrefix,
        ref = ref
    )
}