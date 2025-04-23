package com.example.anime_screen.player_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.NekoViewIcons

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun PlayerFeaturesBottomBar(
    episodeTime: String,
    showPlayerFeatures: Boolean,
    onQuitFullScreenClick: () -> Unit,
    onLockScreenClick: () -> Unit,
    onFillScreenClick: () -> Unit,
    isCropped: Boolean,
    isLandscape: Boolean,
    sliderPosition: Float,
    onSliderValueChange: (Float) -> Unit,
    onSliderValueChangeFinished: () -> Unit,
    onSettingsClick: () -> Unit
) {
    AnimatedVisibility(
        visible = showPlayerFeatures,
        enter = slideInVertically(
            animationSpec = tween(300),
            initialOffsetY = { it / 2 }
        ) + fadeIn(tween(300)),
        exit = slideOutVertically(
            animationSpec = tween(300),
            targetOffsetY = { it / 2 }
        ) + fadeOut(tween(300))
    ) {
        BottomAppBar(
            containerColor = Color.Transparent,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Slider(
                    value = sliderPosition,
                    onValueChange = {
                        onSliderValueChange(it)
                    },
                    onValueChangeFinished = onSliderValueChangeFinished,
                    modifier = Modifier.height(2.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = episodeTime,
                        color = Color(0xffffffff)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconButton(
                            onClick = onLockScreenClick
                        ) {
                            Icon(
                                painter = painterResource(NekoViewIcons.Lock),
                                contentDescription = null,
                                tint = Color(0xffffffff)
                            )
                        }

                        IconButton(
                            onClick = onFillScreenClick
                        ) {
                            val animatedImage = AnimatedImageVector.animatedVectorResource(NekoViewIcons.CropAnimated)
                            val animatedPainter = rememberAnimatedVectorPainter(animatedImageVector = animatedImage, atEnd = !isCropped)

                            Image(
                                painter = animatedPainter,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color(0xffffffff))
                            )
                        }

                        IconButton(
                            onClick = onSettingsClick
                        ) {
                            Icon(
                                painter = painterResource(NekoViewIcons.Settings),
                                contentDescription = null,
                                tint = Color(0xffffffff)
                            )
                        }

                        IconButton(
                            onClick = onQuitFullScreenClick
                        ) {
                            val animatedImage = AnimatedImageVector.animatedVectorResource(NekoViewIcons.FullScreenAnimated)
                            val animatedPainter = rememberAnimatedVectorPainter(animatedImageVector = animatedImage, atEnd = !isLandscape)

                            Image(
                                painter = animatedPainter,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color(0xffffffff))
                            )
                        }
                    }
                }
            }
        }
    }
}