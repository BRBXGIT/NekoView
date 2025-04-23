package com.example.anime_screen.player_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.design_system.theme.mShapes

@Composable
fun BoxScope.SkipOpeningButton(
    onClick: () -> Unit,
    showButton: Boolean,
    timer: Int,
    bottomPadding: Dp
) {
    AnimatedVisibility(
        visible = showButton,
        enter = fadeIn(tween(300)),
        exit = fadeOut(tween(300)),
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(
                end = 16.dp,
                bottom = bottomPadding + 32.dp
            )
            .zIndex(2f)
    ) {
        OutlinedButton(
            onClick = onClick,
            shape = mShapes.small
        ) {
            Text(
                text = "Пропустить $timer"
            )
        }
    }
}