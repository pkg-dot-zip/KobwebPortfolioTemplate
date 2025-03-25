import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import com.varabyte.kobwebx.gradle.markdown.MarkdownEntry
import kotlinx.html.link
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    kotlin("plugin.serialization") version "1.8.0"
}

group = "com.pkg_dot_zip.kobwebportfoliotemplate"
version = "1.0-SNAPSHOT"

kobweb {
    markdown {
        process.set { markdownEntries ->
            generateMarkdown("blog_index.md", buildString {
                appendLine(
                    """
                    ---
                    root: .components.layouts.MarkdownLayout("Blog Posts")
                    ---
                """.trimIndent()
                )
                appendLine("# Blog Index")

                // Post Entry class.
                class Post(
                    var title: String,
                    var author: String,
                    var originalDateTime: String,
                    var lastEditedDateTime: String,
                    var description: String,
                    var entry: MarkdownEntry
                )

                // Collect all blog posts & project pages.
                val posts: ArrayList<Post> = arrayListOf()
                markdownEntries.forEach { entry ->

                    if (entry.filePath.startsWith("blog/") || entry.filePath.startsWith("projects/")) {

                        // Retrieve all values from blog post declaration.
                        val title = entry.frontMatter["title"]?.first() ?: "Untitled"
                        val author = entry.frontMatter["author"]?.first() ?: "Unknown"
                        val originalDateTime = entry.frontMatter["original_date_time"]?.first() ?: "A long time ago!"
                        val lastEditedDateTime =
                            entry.frontMatter["last_edited_date_time"]?.first() ?: "A long time ago!"
                        val description = entry.frontMatter["description"]?.first()
                            ?: "No description found. Click on the title to read more!"

                        posts.add(
                            Post(title, author, originalDateTime, lastEditedDateTime, description, entry)
                        )
                    }
                }

                // Sort them by date.
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                posts.sortByDescending {
                    LocalDateTime.parse(it.originalDateTime, formatter)
                }

                // Then create blog post item for each entry.
                posts.forEach {
                    // Title + Author.
                    appendLine("### [${it.title}](${it.entry.route}) - _${it.author}_")

                    // Times.
                    append("Published: _${it.originalDateTime}_ ")
                    appendLine("Edited: _${it.lastEditedDateTime}_")
                    appendLine()

                    // Desc.
                    appendLine(it.description)
                }
            })
        }
    }

    app {
        index {
            head.add {
                link(rel = "preconnect", href = "https://api.fonts.coollabs.io")
                link(rel = "preconnect", href = "https://rsms.me/")

                // First we add the fonts.
                // Inter.
                link(
                    href = "https://rsms.me/inter/inter.css",
                    rel = "stylesheet"
                )
                // Bitter.
                link(
                    href = "https://api.fonts.coollabs.io/css2?family=Bitter",
                    rel = "stylesheet"
                )
                // Raleway.
                link(
                    href = "https://api.fonts.coollabs.io/css2?family=Raleway",
                    rel = "stylesheet"
                )
                // Rokkitt.
                link(
                    href = "https://api.fonts.coollabs.io/css2?family=Rokkitt",
                    rel = "stylesheet"
                )
                // JOST.
                link(
                    href = "https://api.fonts.coollabs.io/css2?family=Jost",
                    rel = "stylesheet"
                )
                // Major Mono Display.
                link(
                    href = "https://api.fonts.coollabs.io/css2?family=Major+Mono+Display",
                    rel = "stylesheet"
                )
            }

            description.set("Powered by Kobweb - Template made by pkg-dot-zip")
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("kobwebportfoliotemplate" /*, includeServer = true*/)

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
        }

        jsMain.dependencies {
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // This default template uses built-in SVG icons, but what's available is limited.
            // Uncomment the following if you want access to a large set of font-awesome icons:
            implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        jvmMain.dependencies {
//            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
//        }
    }
}
