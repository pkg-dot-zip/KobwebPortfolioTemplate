package io.github.username.projectname

import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.rgb

// Contain all graphics (e.g. font + color) & files related constants.
object Res {
    object Image {
        private const val IMAGE_PATH = "image/"
        const val PERSONAL_PICTURE = "$IMAGE_PATH/fakePerson.jpg"

        // Logo.
        object Logo {
            private const val LOGO_PATH = "${IMAGE_PATH}logo/"
            const val LOGO_DARK = "$LOGO_PATH/logo_example_dark.png"
            const val LOGO_LIGHT = "$LOGO_PATH/logo_example_light.png"
        }

        // Software.
        object Software {
            private const val SOFTWARE = "${IMAGE_PATH}software/"

            const val SOFTWARE_1_LOGO = "${SOFTWARE}SoftwareExample.svg"
            const val SOFTWARE_2_LOGO = "${SOFTWARE}SoftwareExample.svg"
            const val SOFTWARE_3_LOGO = "${SOFTWARE}SoftwareExample.svg"

            object IDE {
                const val IDE_1_LOGO = "${SOFTWARE}SoftwareExample.svg"
                const val IDE_2_LOGO = "${SOFTWARE}SoftwareExample.svg"
                const val IDE_3_LOGO = "${SOFTWARE}SoftwareExample.svg"
            }
        }

        // Skills.
        object Skill {
            private const val SKILL = "${IMAGE_PATH}skills/"
            const val PROGRAMMING_LANGUAGE_1_LOGO = "${SKILL}SkillExample.svg"
            const val PROGRAMMING_LANGUAGE_2_LOGO = "${SKILL}SkillExample.svg"
            const val PROGRAMMING_LANGUAGE_3_LOGO = "${SKILL}SkillExample.svg"
        }

        // Socials.
        object Socials {
            private const val SOCIALS = "${IMAGE_PATH}socials/"
            const val GITHUB = "${SOCIALS}github.svg"
            const val LINKEDIN = "${SOCIALS}linkedin.svg"
            const val TWITTER_X = "${SOCIALS}twitter-x.svg"
        }
    }

    // Fonts.
    object Font {
        const val ARIAL = "Arial"

        // These are added in the build.gradle.kts file.
        const val INTER = "Inter"
        const val BITTER = "Bitter"
        const val RALEWAY = "Raleway"
        const val ROKKITT = "Rokkitt"
        const val JOST = "Jost"
        const val MAJOR_MONO_DISPLAY = "Major Mono Display"
    }

    // Colors (noted in RGB, can also take hex).
    object Color {
        val SOFTWARE_AND_SKILLS_BOX_BORDER_COLOR_LIGHT = rgb(233, 233, 233)
        val SOFTWARE_AND_SKILLS_BORDER_COLOR_DARK = rgb(78, 78, 78)
        val FOOTER_COLOR_LIGHT = rgb(241, 241, 241)
        val FOOTER_COLOR_DARK = rgb(13, 13, 13)
    }
}