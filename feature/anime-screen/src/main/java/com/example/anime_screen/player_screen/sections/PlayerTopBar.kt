package com.example.anime_screen.player_screen.sections

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerTopBar(
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit,
    episodeTitle: String,
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
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(NekoViewIcons.ArrowLeft),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = onMenuClick
            ) {
                Icon(
                    painter = painterResource(NekoViewIcons.Menu),
                    contentDescription = null
                )
            }
        }
    )
}