package com.example.navbar_screens.settings_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.design_system.cards.AnimatedShimmer
import com.example.design_system.theme.NekoViewIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenTopBar(
    userName: String,
    userImageUrl: String,
    loadingState: Boolean,
    onExitClick: () -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    ) {
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(userImageUrl)
                                .crossfade(500)
                                .size(Size.ORIGINAL)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            filterQuality = FilterQuality.Low,
                            contentScale = ContentScale.Crop,
                            loading = { AnimatedShimmer(100.dp, 130.dp) }
                        )
                    }

                    Text(text = userName)
                }
            },
            actions = {
                IconButton(
                    onClick = onExitClick
                ) {
                    Icon(
                        painter = painterResource(NekoViewIcons.Exit),
                        contentDescription = null
                    )
                }
            }
        )

        AnimatedVisibility(
            visible = loadingState,
            enter = fadeIn(tween(300)) + expandVertically(tween(300)),
            exit = fadeOut(tween(300)) + shrinkVertically(tween(300))
        ) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}