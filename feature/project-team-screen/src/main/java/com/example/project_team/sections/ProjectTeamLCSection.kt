package com.example.project_team.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.remote.models.project_team_response.ProjectTeamResponse
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mTypography

@Composable
fun ProjectTeamLCSection(
    projectTeam: ProjectTeamResponse
) {
    if(projectTeam != ProjectTeamResponse()) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Тайминги:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.timing) { timing ->
                Text(
                    text = timing,
                    style = mTypography.bodyLarge
                )
            }

            item {
                Text(
                    text = "Субтитры:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.decor) { decor ->
                Text(
                    text = decor,
                    style = mTypography.bodyLarge
                )
            }

            item {
                Text(
                    text = "Озвучка:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.voice) { voice ->
                Text(
                    text = voice,
                    style = mTypography.bodyLarge
                )
            }

            item {
                Text(
                    text = "Правки:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.editing) {editors ->
                Text(
                    text = editors,
                    style = mTypography.bodyLarge
                )
            }

            item {
                Text(
                    text = "Перевод:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.translator) { translators ->
                Text(
                    text = translators,
                    style = mTypography.bodyLarge
                )
            }

            item {
                Text(
                    text = "Ну и я, разработчик приложения:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            item {
                Text(
                    text = "BRBX",
                    style = mTypography.bodyLarge
                )
            }
        }
    }
}