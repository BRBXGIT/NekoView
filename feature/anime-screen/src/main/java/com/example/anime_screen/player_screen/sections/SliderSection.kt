package com.example.anime_screen.player_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.SliderSection(
    valueRange: Float,
    currentPosition: Float,
    onSliderValueChange: (Float) -> Unit,
    onSliderValueChangeFinished: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
    ) {
        Slider(
            modifier = Modifier.padding(32.dp),
            value = currentPosition,
            onValueChange = { onSliderValueChange(it) },
            onValueChangeFinished = onSliderValueChangeFinished,
            valueRange = 0f..valueRange,
            thumb = {
                Icon(
                    painter = painterResource(NekoViewIcons.Square),
                    contentDescription = null,
                    tint = mColors.primary
                )
            }
        )
    }
}