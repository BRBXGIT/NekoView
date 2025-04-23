package com.example.anime_screen.player_screen.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class SettingsItem(
    val icon: Int,
    val name: String,
    val label: String
)

@Composable
fun SettingsBS(
    onDismissRequest: () -> Unit
) {

}