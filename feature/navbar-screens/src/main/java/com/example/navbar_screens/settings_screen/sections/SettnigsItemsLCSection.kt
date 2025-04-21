package com.example.navbar_screens.settings_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.NekoViewIcons

@Composable
fun SettingsItemsLCSection(
    innerPadding: PaddingValues,
    onSettingsItemClick: (Any) -> Unit,
    onLinkItemClick: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(projectItems) { projectItem ->
            SettingsItem(
                name = projectItem.name,
                icon = projectItem.icon,
                onClick = {  },
            )
        }

        item {
            HorizontalDivider()
        }

        item {
            SettingsItem(
                name = "Настройки",
                icon = NekoViewIcons.SettingsFilled,
                onClick = {  }
            )
        }

        item {
            HorizontalDivider()
        }

        items(linkItems) { linkItem ->
            SettingsItem(
                fromLink = true,
                name = linkItem.name,
                icon = linkItem.icon,
                onClick = { onLinkItemClick(linkItem.url) }
            )
        }
    }
}