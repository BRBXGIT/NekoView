package com.example.anime_screen.player_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.design_system.theme.NekoViewIcons

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun BoxScope.PlayPauseSkipSection(
    isLoading: Boolean,
    size: Int,
    index: Int,
    isPlaying: Boolean,
    onPlayClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    showPlayerFeatures: Boolean
) {
    AnimatedVisibility(
        visible = showPlayerFeatures,
        enter = fadeIn(tween(300)),
        exit = fadeOut(tween(300)),
        modifier = Modifier
            .align(Alignment.Center)
            .zIndex(2f)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = onPreviousClick
            ) {
                Icon(
                    painter = painterResource(NekoViewIcons.PreviousFilled),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = if(index == 0) Color(0xffffffff).copy(alpha = 0.3f) else Color(0xffffffff)
                )
            }

            if(isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = Color(0xffffffff)
                )
            } else {
                IconButton(
                    onClick = onPlayClick
                ) {
                    val animatedImage = AnimatedImageVector.animatedVectorResource(NekoViewIcons.PlayPauseAnimated)
                    val animatedPainter = rememberAnimatedVectorPainter(animatedImageVector = animatedImage, atEnd = isPlaying)

                    Image(
                        painter = animatedPainter,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color(0xffffffff)),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            IconButton(
                onClick = onNextClick
            ) {
                Icon(
                    painter = painterResource(NekoViewIcons.NextFilled),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = if(index + 1 == size) Color(0xffffffff).copy(alpha = 0.3f) else Color(0xffffffff)
                )
            }
        }
    }
}