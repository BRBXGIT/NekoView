package com.example.anime_screen.player_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

@Composable
fun PlayerUnlockButtonBottomBar(
    onClick: () -> Unit,
    showUnlockButton: Boolean,
) {
    AnimatedVisibility(
        visible = showUnlockButton,
        enter = slideInVertically(
            animationSpec = tween(300),
            initialOffsetY = { it / 2 }
        ) + fadeIn(tween(300)),
        exit = slideOutVertically(
            animationSpec = tween(300),
            targetOffsetY = { it / 2 }
        ) + fadeOut(tween(300)),
    ) {
        BottomAppBar(
            containerColor = Color.Transparent,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedButton(
                    onClick = onClick,
                    shape = mShapes.small
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(NekoViewIcons.Unlock),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )

                        Text(
                            text = "Разблокировать",
                            style = mTypography.bodySmall
                        )
                    }
                }
            }
        }
    }
}