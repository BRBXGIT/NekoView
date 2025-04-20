package com.example.anime_screen.player_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

@Composable
fun BoxScope.LockedScreenButton(
    onClick: () -> Unit,
    showUnlockButton: Boolean,
    bottomPadding: Dp
) {
    AnimatedVisibility(
        visible = showUnlockButton,
        enter = fadeIn(tween(300)),
        exit = fadeOut(tween(300)),
        modifier = Modifier
            .padding(bottom = bottomPadding + 8.dp)
            .align(Alignment.BottomCenter)
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