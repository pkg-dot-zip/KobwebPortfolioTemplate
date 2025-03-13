package com.pkg_dot_zip.kobwebportfoliotemplate.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobwebx.markdown.markdown
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.Text
import kotlin.js.Date

@Composable
fun BlogPostHeaderWidget() {
    val ctx = rememberPageContext()

    if (ctx.markdown == null) throw UnsupportedOperationException("ctx markdown is null")
    val author = ctx.markdown!!.frontMatter.getValue("author").single()
    val title = ctx.markdown!!.frontMatter.getValue("title").single()
    val originalPublishedDate = parseDateString(ctx.markdown!!.frontMatter.getValue("original_date_time").single())
    val lastEditedDate = parseDateString(ctx.markdown!!.frontMatter.getValue("last_edited_date_time").single())


    Column {
        H1 {
            Text(title)
        }

        H5 {
            Text("Article by $author")
        }

        H6 {
            Text("Published ${originalPublishedDate.toUTCString()} (last edited ${lastEditedDate.toUTCString()})")
        }
    }
}

// NOTE: Written by AI. 🤖
private fun parseDateString(dateString: String): Date {
    // Split the input string into date and time parts
    val parts = dateString.split(" ")
    if (parts.size != 2) {
        throw IllegalArgumentException("Invalid date format. Expected format: dd/MM/yyyy HH:mm")
    }

    // Split the date part into day, month, and year
    val dateParts = parts[0].split("/")
    if (dateParts.size != 3) {
        throw IllegalArgumentException("Invalid date format. Expected format: dd/MM/yyyy")
    }

    val day = dateParts[0].toInt()
    val month = dateParts[1].toInt() - 1 // Month is 0-indexed in JavaScript Date
    val year = dateParts[2].toInt()

    // Split the time part into hours and minutes
    val timeParts = parts[1].split(":")
    if (timeParts.size != 2) {
        throw IllegalArgumentException("Invalid time format. Expected format: HH:mm")
    }

    val hour = timeParts[0].toInt()
    val minute = timeParts[1].toInt()

    return Date(year, month, day, hour, minute)
}