package com.example.anime_screen.player_screen.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mShapes

data class PlayerSettingsItem(
    val name: String,
    val label: String,
    val type: SettingsItemType
)

enum class SettingsItemType {
    Quality, ShowSkipOpeningButton, AutoSkipOpening, Autoplay
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerSettingsBS(
    showSkipOpeningButton: Boolean?,
    currentVideoQuality: Int?,
    onDismissRequest: () -> Unit,
    onChangeQualityClick: () -> Unit,
    onShowSkipOpeningButtonClick: () -> Unit,
    autoSkipOpening: Boolean?,
    onAutoSkipOpeningClick: () -> Unit,
    autoPlay: Boolean?,
    onAutoPlayClick: () -> Unit
) {
    val playerSettingsItems = listOf(
        PlayerSettingsItem(
            name = "Качество",
            label = currentVideoQuality.toString(),
            type = SettingsItemType.Quality
        ),
        PlayerSettingsItem(
            name = "Кнопка пропуска опенинга",
            label = if(showSkipOpeningButton!!) "Да" else "Нет",
            type = SettingsItemType.ShowSkipOpeningButton
        ),
        PlayerSettingsItem(
            name = "Автоматически пропускать опенинг",
            label = if(autoSkipOpening!!) "Да" else "Нет",
            type = SettingsItemType.AutoSkipOpening
        ),
        PlayerSettingsItem(
            name = "Автовоспроизведение",
            label = if(autoPlay!!) "Да" else "Нет",
            type = SettingsItemType.Autoplay
        ),
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        shape = mShapes.small.copy(bottomEnd = CornerSize(0.dp), bottomStart = CornerSize(0.dp)),
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(playerSettingsItems) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(mShapes.extraSmall)
                        .clickable {
                            when(item.type) {
                                SettingsItemType.Quality -> {
                                    onDismissRequest()
                                    onChangeQualityClick()
                                }
                                SettingsItemType.ShowSkipOpeningButton -> {
                                    onDismissRequest()
                                    onShowSkipOpeningButtonClick()
                                }
                                SettingsItemType.AutoSkipOpening -> {
                                    onDismissRequest()
                                    onAutoSkipOpeningClick()
                                }
                                SettingsItemType.Autoplay -> {
                                    onDismissRequest()
                                    onAutoPlayClick()
                                }
                            }
                        }
                        .padding(8.dp)
                ) {
                    Text("${item.name} (${item.label})")
                }
            }
        }
    }
}