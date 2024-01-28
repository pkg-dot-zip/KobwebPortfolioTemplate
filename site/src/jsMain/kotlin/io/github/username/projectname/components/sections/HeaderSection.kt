package io.github.username.projectname.components.sections

import androidx.compose.runtime.*
import io.github.username.projectname.Config
import io.github.username.projectname.Res
import io.github.username.projectname.util.FontHandler
import com.varabyte.kobweb.compose.css.AlignContent
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.cssRem

@Composable
fun HeaderSection() {
    val ctx = rememberPageContext()

    val siteMap = Config.SiteMap.getSiteMap()

    val isLight = when (ColorMode.current) {
        ColorMode.LIGHT -> true
        ColorMode.DARK -> false
    }

    Column(Modifier.margin(top = 4.cssRem, bottom = 4.cssRem).alignContent(AlignContent.Center)) {

        Row(Modifier.margin(bottom = 4.cssRem)) {
            Image(
                src = if (isLight) Res.Image.Logo.LOGO_LIGHT else Res.Image.Logo.LOGO_DARK,
                width = 200,
                modifier = Modifier.onClick {
                    ctx.router.navigateTo("/")
                }.cursor(Cursor.Pointer)
            )
            SpanText(
                Config.SITE_NAME,
                modifier = Modifier.fontFamily(FontHandler.getFont("sitename")).fontSize(FontSize.XLarge)
                    .align(Alignment.CenterVertically)
            )
        }

        Row(Modifier.id("siteMapRowContainer").align(Alignment.CenterHorizontally)) {
            SimpleGrid(
                numColumns = numColumns(2 + (siteMap.size % 2)),
                modifier = Modifier.id("siteMapGrid").alignContent(AlignContent.Center)
            ) {
                generateSiteMap(ctx, siteMap)
            }
        }
    }
}

@Composable
private fun generateSiteMap(ctx: PageContext, siteMap: Map<String, String>) {
    for (entry in siteMap) {
        Row(Modifier.id("siteMapEntryRow").justifyContent(JustifyContent.Center)) {
            var hoveredOver by remember { mutableStateOf(false) }

            SpanText(
                text = entry.key, modifier = Modifier.onClick {
                    ctx.router.navigateTo(entry.value)
                }.onMouseEnter {
                    hoveredOver = true
                }.onMouseLeave {
                    hoveredOver = false
                }.fontFamily(FontHandler.getFont("headersitemap")).fontSize(FontSize.Larger)
                    .margin(0.cssRem)
                    .fontStyle(if (hoveredOver) FontStyle.Italic else FontStyle.Normal)
                    .cursor(Cursor.Pointer)
            )
        }
    }
}