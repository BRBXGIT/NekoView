package com.example.anime_screen.anime_screen.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

@Composable
fun EpisodeItem(
    episode: Int,
    name: String,
    onWatchButtonClick: () -> Unit,
    watched: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = if(watched) mColors.surfaceContainer else mColors.surfaceContainerHigh,
                shape = mShapes.small
            )
            .padding(8.dp),
    ) {
        Text(
            text = "$episode • $name",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = mTypography.bodyLarge,
            modifier = Modifier
                .alpha(if(watched) 0.8f else 1f)
                .weight(1f)
                .padding(end = 16.dp)
        )

        TextButton(
            onClick = onWatchButtonClick,
            modifier = Modifier.alpha(if(watched) 0.8f else 1f)
        ) {
            Text(
                text = "Смотреть",
                modifier = Modifier.alpha(if(watched) 0.8f else 1f)
            )
        }
    }
}