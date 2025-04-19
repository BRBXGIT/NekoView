package com.example.anime_screen.anime_screen.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mTypography

@Composable
fun TitleTeamSection(
    voiceActors: String,
    timingWorkers: String,
    subtitlesWorkers: String
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Озвучка: $voiceActors",
            style = mTypography.bodyMedium
        )

        Text(
            text = "Тайминг: $timingWorkers",
            style = mTypography.bodyMedium
        )

        Text(
            text = "Работа над субтитрами: $subtitlesWorkers",
            style = mTypography.bodyMedium
        )
    }
}