package com.pkg_dot_zip.kobwebportfoliotemplate

import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.rgb

object Res {
    private const val IMAGE_PATH = "image/"
    const val LOGO_LIGHT = "$IMAGE_PATH/kobweb-logo.png"
    const val LOGO_DARK = "$IMAGE_PATH/kobweb-logo.png"
    const val OCTOCAT = "$IMAGE_PATH/octocat.png" // Created with https://myoctocat.com/build-your-octocat/

    object Socials {
        private const val SOCIALS = "${IMAGE_PATH}socials/"
        const val GITHUB_LOGO = "${SOCIALS}github.svg"
        const val LINKEDIN_LOGO = "${SOCIALS}linkedin.svg"
    }

    object Languages {
        private const val LANG = "${IMAGE_PATH}programLang/"
        const val CSS_LOGO = "${LANG}CSS.svg"
        const val FANTOM_LOGO = "${LANG}Fantom.svg"
        const val GOLANG_LOGO = "${LANG}GoLang.svg"
        const val CSHARP_LOGO = "${LANG}CSharp.svg"
        const val C_LOGO = "${LANG}C.svg"
        const val CPP_LOGO = "${LANG}Cpp.svg"
        const val PYTHON_LOGO = "${LANG}Python.svg"
        const val HTML_LOGO = "${LANG}HTML.svg"
        const val JAVA_LOGO = "${LANG}Java.svg"
        const val JAVASCRIPT_LOGO = "${LANG}Javascript.svg"
        const val KOTLIN_LOGO = "${LANG}Kotlin.svg"
        const val MARKDOWN_LOGO = "${LANG}Markdown.svg"
    }

    object Frameworks {
        private const val FRAMEWORKS = "${IMAGE_PATH}frameworks/"
        const val KOBWEB_LOGO = "${FRAMEWORKS}Kobweb.png"
        const val JUNIT5_LOGO = "https://junit.org/junit5/assets/img/junit5-logo.png"
        const val RAYLIB_LOGO = "https://www.raylib.com/common/img/raylib_logo.png"
        const val KOTLIN_POET_LOGO = "https://square.github.io/kotlinpoet/images/icon-square.png"
        const val GLFW_LOGO = "https://camo.githubusercontent.com/a866ade87832741c26e7f3bfb1ec9ae03c9b6f22f0c87bdf2a734f1dc8f3f57f/68747470733a2f2f7777772e676c66772e6f72672f696d672f66617669636f6e2f66617669636f6e2d313936783139362e706e67"
        const val UTEST_LOGO = "https://openmoji.org/data/color/svg/1F9EA.svg"
        const val OPENCV_LOGO = "https://upload.wikimedia.org/wikipedia/commons/3/32/OpenCV_Logo_with_text_svg_version.svg"
        const val KORD_LOGO = "https://avatars.githubusercontent.com/u/54203153?s=280&v=4"
    }

    object Software {
        private const val SOFTWARE = "${IMAGE_PATH}software/"
        const val INTELLIJ_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.svg"
        const val AQUA_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/Aqua_icon.svg"
        const val CLION_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/CLion_icon.svg"
        const val DATAGRIP_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/DataGrip_icon.svg"
        const val DATALORE_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/Datalore_icon.svg"
        const val DATASPELL_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/DataSpell_icon.svg"
        const val DOTCOVER_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/dotCover_icon.svg"
        const val DOTMEMORY_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/dotMemory_icon.svg"
        const val DOTPEEK_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/dotPeek_icon.svg"
        const val DOTTRACE_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/dotTrace_icon.svg"
        const val FLEET_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/Fleet_icon.svg"
        const val GOLAND_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/GoLand_icon.svg"
        const val HUB_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/Hub_icon.svg"
        const val KTOR_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/Ktor_icon.svg"
        const val LICENSE_VAULT_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/JetBrains_LicenseVault_icon.svg"
        const val MPS_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/MPS_icon.svg"
        const val PHP_STORM_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/PhpStorm_icon.svg"
        const val PYCHARM_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/PyCharm_icon.svg"
        const val QODANA_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/Qodana_icon.svg"
        const val RESHARPER_CSHARP_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/ReSharper_icon.svg"
        const val RESHARPER_CPP_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/ReSharperCPP_icon.svg"
        const val RIDER_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/Rider_icon.svg"
        const val RIDERFLOW_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/RiderFlow_icon.svg"
        const val RUBYMINE_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/RubyMine_icon.svg"
        const val RUSTROVER_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/RustRover_icon.svg"
        const val TEAMCITY_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/TeamCity_icon.svg"
        const val TOOLBOX_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/Toolbox_icon.svg"
        const val WEBSTORM_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/WebStorm_icon.svg"
        const val YOUTRACK_LOGO = "https://resources.jetbrains.com/storage/products/company/brand/logos/YouTrack_icon.svg"
        const val VS_LOGO = "https://upload.wikimedia.org/wikipedia/commons/5/59/Visual_Studio_Icon_2019.svg"
        const val XAMPP_LOGO = "https://www.svgrepo.com/show/354575/xampp.svg"
        const val FDROID_LOGO = "https://upload.wikimedia.org/wikipedia/commons/3/3c/F-Droid_Logo_4.svg"
        const val POSTMAN_LOGO = "https://www.svgrepo.com/show/354202/postman-icon.svg"
    }

    object AnimatedEmojis {
        private const val BASE_URL = "https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/"
        const val ASTONISHED_FACE = "${BASE_URL}Smilies/Astonished%20Face.png"
        const val DESKTOP_COMPUTER = "${BASE_URL}Objects/Desktop%20Computer.png"
        const val COMPUTER_DISK = "${BASE_URL}Objects/Computer%20Disk.png"
        const val COMPUTER_MOUSE = "${BASE_URL}Objects/Computer%20Mouse.png"
        const val BUBBLES = "${BASE_URL}Symbols/Bubbles.png"
        const val HAMSA = "${BASE_URL}Symbols/Hamsa.png"
        const val FACTORY_WORKER = "${BASE_URL}People/Factory%20Worker.png"
        const val FARMER = "${BASE_URL}People/Farmer.png"
        const val FIREFIGHTER = "${BASE_URL}People/Firefighter.png"
        const val MAN_BOUNCING_BALL = "${BASE_URL}People/Man%20Bouncing%20Ball.png"
        const val MAN_CARTWHEELING = "${BASE_URL}People/Man%20Cartwheeling.png"
        const val MAN_CLIMBING = "${BASE_URL}People/Man%20Climbing.png"
        const val MAN_BIKING = "${BASE_URL}People/Man%20Biking.png"
        const val MAN_DANCING = "${BASE_URL}People/Man%20Dancing.png"
        const val MAN_JUGGLING = "${BASE_URL}People/Man%20Juggling.png"
        const val MAN_SURFING = "${BASE_URL}People/Man%20Surfing.png"
        const val MAN_SWIMMING = "${BASE_URL}People/Man%20Swimming.png"
        const val MAN_TECHNOLOGIST = "${BASE_URL}People/Man%20Technologist.png"
        const val MAN_TIPPING_HAND = "${BASE_URL}People/Man%20Tipping%20Hand.png"
        const val MAN_IN_LOTUS_POSITION = "${BASE_URL}People/Man%20in%20Lotus%20Position.png"
        const val MAN_LIFTING_WEIGHTS = "${BASE_URL}People/Man%20Lifting%20Weights.png"
        const val NINJA = "${BASE_URL}People/Ninja.png"
        const val SPEAKING_HEAD = "${BASE_URL}People/Speaking%20Head.png"
        const val FACE_IN_CLOUDS = "${BASE_URL}Smilies/Face%20in%20Clouds.png"
        const val ROBOT = "${BASE_URL}Smilies/Robot.png"
        const val KEYBOARD = "${BASE_URL}Objects/Keyboard.png"
        const val SMILING_FACE_WITH_SUNGLASSES = "${BASE_URL}Smilies/Smiling%20Face%20with%20Sunglasses.png"
        const val DOTTED_LINE_FACE = "${BASE_URL}Smilies/Dotted%20Line%20Face.png"
        const val NERD_FACE = "${BASE_URL}Smilies/Nerd%20Face.png"
        const val STAR_STRUCK = "${BASE_URL}Smilies/Star-Struck.png"
        const val PARTYING_FACE = "${BASE_URL}Smilies/Partying%20Face.png"
        const val CROSSED_FLAGS = "${BASE_URL}Symbols/Crossed%20Flags.png"
        const val SNOWMAN = "${BASE_URL}Travel%20and%20places/Snowman.png"
        const val WHITE_FLAG = "${BASE_URL}Symbols/White%20Flag.png"
    }

    object Video {
        private const val VIDEO = "video/"
        const val CODING = "${VIDEO}10130349-sd_640_360_30fps.mp4"
    }

    // Colors (noted in RGB, can also take hex).
    object Color {
        val SOFTWARE_AND_SKILLS_BOX_BORDER_COLOR_LIGHT = rgb(233, 233, 233)
        val SOFTWARE_AND_SKILLS_BORDER_COLOR_DARK = rgb(78, 78, 78)
    }
}