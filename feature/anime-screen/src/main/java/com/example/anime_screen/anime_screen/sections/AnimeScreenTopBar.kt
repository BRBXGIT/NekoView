package com.example.anime_screen.anime_screen.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mColors

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimeScreenTopBar(
    showHeartIcon: Boolean,
    titleName: String,
    onHeartIconClick: () -> Unit,
    onBackClick: () -> Unit,
    loadingState: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    isFeatured: Boolean
) {
    Column {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = mColors.surfaceContainer.copy(alpha = 0f)
            ),
            scrollBehavior = scrollBehavior,
            title = {
                AnimatedVisibility(
                    visible = scrollBehavior.state.contentOffset <= -600f,
                    enter = fadeIn(tween(300)),
                    exit = fadeOut(tween(300))
                ) {
                    Text(
                        text = titleName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            },
            actions = {
                if(showHeartIcon) {
                    IconButton(
                        onClick = {
                            onHeartIconClick()
                        }
                    ) {
                        val animatedImage = AnimatedImageVector.animatedVectorResource(NekoViewIcons.HeartAnimated)
                        val animatedPainter = rememberAnimatedVectorPainter(animatedImageVector = animatedImage, atEnd = isFeatured)

                        Image(
                            painter = animatedPainter,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(mColors.onSurfaceVariant)
                        )
                    }
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        painter = painterResource(id = NekoViewIcons.ArrowLeftFilled),
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