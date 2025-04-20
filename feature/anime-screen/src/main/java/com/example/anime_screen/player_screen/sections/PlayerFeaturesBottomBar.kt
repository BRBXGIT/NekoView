package com.example.anime_screen.player_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.NekoViewIcons

@Composable
fun PlayerFeaturesBottomBar(
    episodeTime: String,
    showPlayerFeatures: Boolean,
    onQuitFullScreenClick: () -> Unit,
    onLockScreenClick: () -> Unit,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = episodeTime
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = onLockScreenClick
                    ) {
                        Icon(
                            painter = painterResource(NekoViewIcons.Lock),
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = {  }
                    ) {
                        Icon(
                            painter = painterResource(NekoViewIcons.FullScreen),
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = {  }
                    ) {
                        Icon(
                            painter = painterResource(NekoViewIcons.Settings),
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = onQuitFullScreenClick
                    ) {
                        Icon(
                            painter = painterResource(NekoViewIcons.QuitFullScreen),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}