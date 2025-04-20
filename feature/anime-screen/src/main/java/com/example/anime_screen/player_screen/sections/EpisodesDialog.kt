package com.example.anime_screen.player_screen.sections

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesDialog(
    onConfirmClick: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    currentEpisodeIndex: Int,
    episodes: List<String>
) {
    var currentEpisodeIndexState by rememberSaveable { mutableIntStateOf(currentEpisodeIndex) }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.background(
            color = mColors.surfaceContainerHigh,
            shape = mShapes.small
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = "Выбор серии",
                style = mTypography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            HorizontalDivider()

            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(episodes) { index, episode ->
                    EpisodeItem(
                        onClick = { currentEpisodeIndexState = index },
                        episode = "${index + 1} · $episode",
                        isChosen = currentEpisodeIndexState == index
                    )
                }
            }

            HorizontalDivider()

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text(text = "Отмена")
                }

                TextButton(
                    onClick = {
                        onConfirmClick(currentEpisodeIndexState)
                        onDismissRequest()
                    }
                ) {
                    Text(text = "Выбрать")
                }
            }
        }
    }
}

@Composable
private fun EpisodeItem(
    episode: String,
    onClick: () -> Unit,
    isChosen: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(mShapes.extraSmall)
            .clickable { onClick() }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = episode,
            style = mTypography.bodyMedium
        )

        val animatedIconAlpha by animateFloatAsState(
            targetValue = if (isChosen) 1.0f else 0f,
            animationSpec = tween(300),
            label = "Animated icon alpha"
        )
        Icon(
            painter = painterResource(NekoViewIcons.Square),
            contentDescription = null,
            tint = mColors.primary,
            modifier = Modifier
                .size(16.dp)
                .graphicsLayer {
                    alpha = animatedIconAlpha
                }
        )
    }
}