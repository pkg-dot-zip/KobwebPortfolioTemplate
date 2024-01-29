package io.github.username.projectname.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import io.github.username.projectname.Config
import org.jetbrains.compose.web.dom.A


enum class ProfileBadgeTheme(val value: String) {
    DARK("dark"),
    LIGHT("light"),
}

@Composable
fun LinkedInProfileBadge(
    username: String = Config.Socials.LINKEDIN_USERNAME,
    theme: ProfileBadgeTheme = ProfileBadgeTheme.LIGHT
) {
    Box(
        modifier = Modifier.attrsModifier {
            attr("data-locale", "nl_NL")
            attr("data-size", "medium")
            attr("data-theme", theme.value)
            attr("data-type", "VERTICAL")
            attr("data-vanity", username)
            attr("data-version", "v1")
        }.classNames("badge-base", "LI-profile-badge")
    ) {
        A(href = "https://nl.linkedin.com/in/${username}?trk=profile-badge") {
        }
    }
}