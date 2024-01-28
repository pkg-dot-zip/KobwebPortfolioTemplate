package io.github.username.projectname

import com.varabyte.kobweb.silk.theme.colors.ColorMode
import io.github.username.projectname.components.widgets.RepoImageFont
import io.github.username.projectname.components.widgets.RepoImagePattern
import io.github.username.projectname.util.LinkableImage
import io.github.username.projectname.util.github.RepoHandler
import io.github.username.projectname.util.github.Repository

object Config {

    // Base.
    const val IS_DEBUGGING = true           // Use this when developing the site.
    const val SITE_NAME = "my-portfolio"    // Site name. Shown at the top of the site and next to the logo.
    val DEFAULT_COLOR = ColorMode.DARK      // The color the site will have upon visiting.

    // Theme Button.
    const val IS_THEME_BUTTON_ROUND = true  // Button is round on true, if not it'll be square.


    val ABOUT_ME_TEXT: String = """
        Hello! I'm a ?-year-old ambitious and dedicated individual. My journey is focused on blending a strong theoretical foundation with practical skills in software engineering.
    """.trimIndent()

    object Video {
        const val SHOW_VIDEO = true         // Adds video section if true.
        const val VIDEO_EMBED_URL = "https://www.youtube.com/embed/m4Cqz2_P9rI?si=O0zJVToM-fv_xkpX"
    }

    object Repositories {
        // All values for the repository image on the index page.
        // If you want to test values, you can go to: https://socialify.git.ci/
        val REPO_IMAGE_FONT: RepoImageFont = RepoImageFont.INTER            // Font used.
        val REPO_IMAGE_PATTERN: RepoImagePattern = RepoImagePattern.SOLID   // The background pattern.
        const val REPO_IMAGE_SHOW_OWNER: Boolean = true                     // To show the "owner/" before repo name.
        const val REPO_IMAGE_SHOW_NAME: Boolean = true                      // To show name after the "owner/".
        const val REPO_IMAGE_SHOW_DESCRIPTION: Boolean = true               // To show description under name.
        const val REPO_IMAGE_SHOW_LANGUAGE: Boolean =
            true                  // To show language icon next to GitHub logo.
        const val REPO_IMAGE_SHOW_STARS: Boolean = true                     // To show stars at bottom.
        const val REPO_IMAGE_SHOW_FORKS: Boolean = false                    // To show forks at bottom.
        const val REPO_IMAGE_SHOW_ISSUES: Boolean = false                   // To show issues at bottom.
        const val REPO_IMAGE_SHOW_PULL_REQUESTS: Boolean = false            // To show PRs at bottom.


        // This will configure what repositories will be shown on the index page.
        val REPOSITORY_SHOWING_MODE = RepoHandler.RepositoryShowingMode.FEATURED

        // Only takes effect if REPOSITORY_SHOWING_MODE is FEATURED.
        val REPOSITORY_FEATURES_LIST = arrayOf(
            "kobweb",
            "kotter",
            "truthish",
        )

        // List of reasons to SKIP a repo on the entire site.
        fun getReasonsToSkipRepo(repository: Repository) = arrayOf(
            repository.fork!!, // If forked we skip.
            repository.archived!!, // If archived we skip.
            repository.name!!.endsWith("ForbiddenSuffixHere!"), // Example of custom reason to skip a repository.
        )

        // List of exceptions in case we want to KEEP a repo on the entire site.
        fun getReasonsToKeepRepo(repository: Repository) = arrayOf(
            repository.name!! == "NameWeWantToKeepDespiteBeingInReasonToSkip", // Example of custom reason to keep a repo as an exception.
        )
    }

    object SiteMap {
        fun getSiteMap(): Map<String, String> {
            return if (IS_DEBUGGING) siteMap.plus(siteDebugMap)
            else siteMap
        }

        // This contains all the pages you can click on at the top of your site.
        private val siteMap: Map<String, String> =
            mapOf(
                Pair("Home", "/"),
                Pair("Repositories", "/repositories"),
            )

        // This contains pages you can click on too if IS_DEBUGGING is set to true.
        private val siteDebugMap: Map<String, String> =
            mapOf(
                Pair("FontTest", "/fonttest")
            )
    }

    object Socials {
        // Your socials that will be linked to and/or data will be pulled from.
        const val MAIL = "your-mail@gmail.com"          // Used in footer for link.
        const val LINKEDIN_USERNAME = ""                // Used in footer for link.
        const val GITHUB_USERNAME = "varabyte"          // Used in footer for link & for GitHub API.
        const val TWITTER_USERNAME = ""                 // Used in footer for link.

        // Returns image used in the footer together with the full link.
        fun getSocials(): Map<String, String> {
            return mapOf(
                Pair(Consts.LINKEDIN_URL, Res.Image.Socials.LINKEDIN),
                Pair(Consts.GITHUB_URL, Res.Image.Socials.GITHUB),
                Pair(Consts.TWITTER_URL, Res.Image.Socials.TWITTER_X),
            )
        }
    }

    object Fonts {
        // Fonts that will be used in the FontTestSection, available on the FontTest page
        // visible when in debug mode.
        val TEST_FONTS: Array<String> = arrayOf(
            Res.Font.INTER,
            Res.Font.BITTER,
            Res.Font.RALEWAY,
            Res.Font.ROKKITT,
            Res.Font.JOST,
            Res.Font.MAJOR_MONO_DISPLAY,
            Res.Font.ARIAL,
        )

        // Fonts used for different types of text on the site.
        val FONT_MAP: Map<String, String> =
            mapOf(
                Pair("sitename", Res.Font.MAJOR_MONO_DISPLAY),
                Pair("headersitemap", Res.Font.ARIAL),
                Pair("h1", Res.Font.INTER),
                Pair("text", Res.Font.INTER),
                Pair("repotext", Res.Font.INTER),
                Pair("repostarwatch", Res.Font.MAJOR_MONO_DISPLAY),
                Pair("footer", Res.Font.INTER),
            )
    }

    object SoftwareAndSkills {
        // Here you can add more boxes or change the order.
        fun getMap(): Map<String, Array<LinkableImage>> = mapOf(
            Pair("IDEs", IDEs),
            Pair("Software", SOFTWARE),
            Pair("Programming Languages", PROGRAMMING_LANGUAGES),
        )

        // All IDEs you use.
        private val IDEs: Array<LinkableImage> =
            arrayOf(
                LinkableImage(Consts.SoftwareUrl.IDE.IDE_1_URL, Res.Image.Software.IDE.IDE_1_LOGO),
                LinkableImage(Consts.SoftwareUrl.IDE.IDE_2_URL, Res.Image.Software.IDE.IDE_2_LOGO),
                LinkableImage(Consts.SoftwareUrl.IDE.IDE_3_URL, Res.Image.Software.IDE.IDE_3_LOGO),
            )

        // Other (relevant) software you use.
        private val SOFTWARE: Array<LinkableImage> =
            arrayOf(
                LinkableImage(Consts.SoftwareUrl.SOFTWARE_1_URL, Res.Image.Software.SOFTWARE_1_LOGO),
                LinkableImage(Consts.SoftwareUrl.SOFTWARE_2_URL, Res.Image.Software.SOFTWARE_2_LOGO),
                LinkableImage(Consts.SoftwareUrl.SOFTWARE_3_URL, Res.Image.Software.SOFTWARE_3_LOGO),
            )

        // Programming language you are proficient in.
        private val PROGRAMMING_LANGUAGES: Array<LinkableImage> =
            arrayOf(
                LinkableImage(Consts.ProgrammingLangUrl.PROGRAMMING_LANGUAGE_1_URL, Res.Image.Skill.PROGRAMMING_LANGUAGE_1_LOGO),
                LinkableImage(Consts.ProgrammingLangUrl.PROGRAMMING_LANGUAGE_2_URL, Res.Image.Skill.PROGRAMMING_LANGUAGE_2_LOGO),
                LinkableImage(Consts.ProgrammingLangUrl.PROGRAMMING_LANGUAGE_3_URL, Res.Image.Skill.PROGRAMMING_LANGUAGE_3_LOGO),
            )
    }
}