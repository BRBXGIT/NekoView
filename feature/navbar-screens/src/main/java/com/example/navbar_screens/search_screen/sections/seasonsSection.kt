package com.example.navbar_screens.search_screen.sections

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

fun LazyGridScope.seasonSection(
    seasons: List<String>,
    onSeasonClick: (String) -> Unit,
    chosenSeasons: List<String>
) {
    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Text(
            text = "Сезон",
            style = mTypography.titleMedium
        )
    }

    items(seasons) { season ->
        val surfaceAnimatedColor by animateColorAsState(
            targetValue = if(season in chosenSeasons) mColors.primary else mColors.surfaceContainerHigh,
            animationSpec = tween(200)
        )
        val onSurfaceAnimatedColor by animateColorAsState(
            targetValue = if(season in chosenSeasons) mColors.onPrimary else mColors.onSurface,
            animationSpec = tween(200)
        )

        Surface(
            color = surfaceAnimatedColor,
            shape = mShapes.small,
            onClick = { onSeasonClick(season) }
        ) {
            Text(
                color = onSurfaceAnimatedColor,
                text = season.toString(),
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}