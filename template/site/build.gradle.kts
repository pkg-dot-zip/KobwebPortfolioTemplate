import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link

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
