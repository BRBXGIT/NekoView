package com.example.navbar_screens.settings_screen.sections

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

enum class ProjectItemType {
    PROJECT_GROUP, SUPPORT
}

data class ProjectItem(
    val type: ProjectItemType,
    val name: String,
    val icon: Int
)

val projectItems = listOf(
    ProjectItem(
        type = ProjectItemType.PROJECT_GROUP,
        name = "Команда проекта",
        icon = NekoViewIcons.Group
    ),
    ProjectItem(
        type = ProjectItemType.SUPPORT,
        name = "Поддержать",
        icon = NekoViewIcons.Gift
    )
)

data class LinkItem(
    val name: String,
    val icon: Int,
    val url: String
)

val linkItems = listOf(
    LinkItem(
        name = "Группа VK",
        icon = NekoViewIcons.VKMulticolored,
        url = "https://vk.com/anilibria"
    ),
    LinkItem(
        name = "Канал YouTube",
        icon = NekoViewIcons.YouTubeMulticolored,
        url = "https://www.youtube.com/@anilibriatv"
    ),
    LinkItem(
        name = "Patreon",
        icon = NekoViewIcons.PatreonMulticolored,
        url = "https://patreon.com/anilibria"
    ),
    LinkItem(
        name = "Канал Telegram",
        icon = NekoViewIcons.TelegramMulticolored,
        url = "https://t.me/anilibria_tv"
    ),
    LinkItem(
        name = "Чат Discord",
        icon = NekoViewIcons.DiscordMulticolored,
        url = "https://discord.gg/M6yCGeGN9B"
    ),
    LinkItem(
        name = "Сайт AniLibria",
        icon = NekoViewIcons.AniLibriaMulticolored,
        url = "https://anilibria.top"
    ),
    LinkItem(
        name = "Наши приложения",
        icon = NekoViewIcons.AniLibriaMulticolored,
        url = "https://anilibria.app"
    )
)

@Composable
fun SettingsItem(
    fromLink: Boolean = false,
    name: String,
    icon: Int,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(mShapes.small)
            .clickable { onClick }
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = if(fromLink) Color.Unspecified else mColors.onBackground
        )

        Text(
            text = name,
            style = mTypography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}