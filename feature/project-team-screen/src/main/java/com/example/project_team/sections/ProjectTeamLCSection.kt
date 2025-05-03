package com.example.project_team.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                Text(
                    text = "Тайминги:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.timing) { timing ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    tonalElevation = 2.dp,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = timing,
                        style = mTypography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            item(
                span = { GridItemSpan(maxLineSpan) }
            )  {
                Text(
                    text = "Субтитры:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.decor) { decor ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    tonalElevation = 2.dp,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = decor,
                        style = mTypography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            item(
                span = { GridItemSpan(maxLineSpan) }
            )  {
                Text(
                    text = "Озвучка:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.voice) { voice ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    tonalElevation = 2.dp,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = voice,
                        style = mTypography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            item(
                span = { GridItemSpan(maxLineSpan) }
            )  {
                Text(
                    text = "Правки:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.editing) { editors ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    tonalElevation = 2.dp,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = editors,
                        style = mTypography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            item(
                span = { GridItemSpan(maxLineSpan) }
            )  {
                Text(
                    text = "Перевод:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            items(projectTeam.translator) { translators ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    tonalElevation = 2.dp,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = translators,
                        style = mTypography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            item(
                span = { GridItemSpan(maxLineSpan) }
            )  {
                Text(
                    text = "Ну и я, разработчик приложения:",
                    style = mTypography.titleLarge.copy(
                        color = mColors.primary
                    )
                )
            }

            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    tonalElevation = 2.dp,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "BRBX",
                        style = mTypography.bodyLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}