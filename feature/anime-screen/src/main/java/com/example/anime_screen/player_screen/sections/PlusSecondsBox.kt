package com.example.anime_screen.player_screen.sections

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.example.design_system.theme.mShapes
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.PlusSecondsBox(
    onDoubleClick: () -> Unit,
    direction: Direction
) {
    var skip by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(skip) {
        if(skip) {
            delay(300)
            skip = false
        }
    }
    val animatedLabelAlpha by animateFloatAsState(
        targetValue = if(skip) 1f else 0f,
        animationSpec = tween(300),
        label = "Animated alpha for label"
    )
    val animatedBoxColor by animateColorAsState(
        targetValue = if(skip) Color.Gray.copy(alpha = 0.3f) else Color.Transparent,
        animationSpec = tween(300),
        label = "Animated color for box"
    )

    Box(
        modifier = Modifier
            .then(
                if(direction == Direction.Plus) {
                    Modifier.align(Alignment.CenterEnd)
                } else {
                    Modifier.align(Alignment.CenterStart)
                }
            )
            .fillMaxWidth(0.25f)
            .fillMaxHeight()
            .background(
                color = animatedBoxColor,
                shape = mShapes.large
            )
            .combinedClickable(
                onClick = { },
                onDoubleClick = {
                    onDoubleClick()
                    skip = true
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if(direction == Direction.Plus) "+5 секунд" else "-5 секунд",
            modifier = Modifier
                .graphicsLayer {
                    alpha = animatedLabelAlpha
                }
        )
    }
}

enum class Direction{
    Plus, Minus
}