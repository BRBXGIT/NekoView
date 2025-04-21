package com.example.anime_screen.player_screen.sections

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mTypography
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerTopBar(
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit,
    episodeTitle: String,
    showPlayerFeatures: Boolean
) {
    AnimatedVisibility(
        visible = showPlayerFeatures,
        enter = slideInVertically(tween(300)) + fadeIn(tween(300)),
        exit = shrinkVertically(tween(300)) + fadeOut(tween(300))
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            title = {
                Text(
                    text = episodeTitle,
                    style = mTypography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xffffffff)
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        painter = painterResource(NekoViewIcons.ArrowLeftFilled),
                        contentDescription = null,
                        tint = Color(0xffffffff)
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = onMenuClick
                ) {
                    Icon(
                        painter = painterResource(NekoViewIcons.Menu),
                        contentDescription = null,
                        tint = Color(0xffffffff)
                    )
                }
            }
        )
    }
}