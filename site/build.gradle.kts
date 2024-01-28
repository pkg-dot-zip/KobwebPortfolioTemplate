import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

group = "io.github.username.projectname"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            head.add {
                // First we add the fonts.
                // Inter.
                link(rel = "preconnect", href = "https://rsms.me/")
                link(
                    href = "https://rsms.me/inter/inter.css",
                    rel = "stylesheet"
                )
                // Bitter.
                link(
                    href = "https://fonts.googleapis.com/css?family=Bitter",
                    rel = "stylesheet"
                )
                // Raleway.
                link(
                    href = "https://fonts.googleapis.com/css?family=Raleway",
                    rel = "stylesheet"
                )
                // Rokkitt.
                link(
                    href = "https://fonts.googleapis.com/css?family=Rokkitt",
                    rel = "stylesheet"
                )
                // JOST.
                link(
                    href = "https://fonts.googleapis.com/css?family=Jost",
                    rel = "stylesheet"
                )
                // Major Mono Display.
                link(
                    href = "https://fonts.googleapis.com/css?family=Major+Mono+Display",
                    rel = "stylesheet"
                )
            }

            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    configAsKobwebApplication("projectname")

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
        }

        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
            
        }
    }
}
