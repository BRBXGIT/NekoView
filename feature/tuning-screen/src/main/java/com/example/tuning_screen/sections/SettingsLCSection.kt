package com.example.tuning_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.AppThemeVM
import com.example.design_system.theme.mTypography

enum class PlayerSettingsItemType {
    VideoQuality, ShowSkipOpeningButton, AutoSkipOpening, AutoPlay
}

data class PlayerSettingsItem(
    val name: String,
    val label: String,
    val type: PlayerSettingsItemType
)

@Composable
fun SettingsLCSection(
    appThemeVM: AppThemeVM,
    chosenTheme: String,
    chosenColorSystem: String,
    videoQuality: Int
) {
    val playerSettingsItems = listOf(
        PlayerSettingsItem(
            name = "Качество",
            label = videoQuality.toString(),
            type = PlayerSettingsItemType.VideoQuality
        ),
        PlayerSettingsItem(
            name = "Кнопка пропуска",
            label = "Показывать кнопку пропуска опенинга",
            type = PlayerSettingsItemType.VideoQuality
        ),
        PlayerSettingsItem(
            name = "Автоматический пропуск",
            label = "Автоматически пропускать опенинг",
            type = PlayerSettingsItemType.VideoQuality
        ),
        PlayerSettingsItem(
            name = "Автовоспроизведение",
            label = "Автоматически воспроизводить следующий эпизод",
            type = PlayerSettingsItemType.VideoQuality
        )
    )

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Тема",
                style = mTypography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ThemePreviewsSection(
                    viewModel = appThemeVM,
                    chosenTheme = chosenTheme
                )

                ColorSystemElements(
                    chosenTheme = chosenTheme,
                    onColorSystemClick = { appThemeVM.changeColorSystem(it) },
                    chosenColorSystem = chosenColorSystem
                )
            }
        }

        item {
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        }

        item {
            Text(
                text = "Плеер",
                style = mTypography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}