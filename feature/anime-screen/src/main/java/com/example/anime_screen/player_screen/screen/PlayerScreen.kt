package com.example.anime_screen.player_screen.screen

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import com.example.anime_screen.player_screen.sections.AnimePlayer
import com.example.anime_screen.player_screen.sections.SliderSection
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationGraphicsApi::class, ExperimentalFoundationApi::class)
@Composable
fun PlayerScreen(
    navController: NavController,
    videoUrl: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(videoUrl)
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            }
        }

        AnimePlayer(
            modifier = Modifier.fillMaxSize(),
            exoPlayer = exoPlayer,
            context = context
        )

        val activity = context as? Activity
        DisposableEffect(Unit) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            onDispose {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                exoPlayer.release()
            }
        }

        //Progress and time
        var currentPosition by rememberSaveable { mutableLongStateOf(0L) }
        val duration = exoPlayer.duration.coerceAtLeast(1L)

        LaunchedEffect(Unit) {
            while(true) {
                currentPosition = exoPlayer.currentPosition
                delay(500)
            }
        }

        SliderSection(
            valueRange = duration.toFloat(),
            currentPosition = currentPosition.toFloat(),
            onSliderValueChange = { currentPosition = it.toLong() },
            onSliderValueChangeFinished = { exoPlayer.seekTo(currentPosition) }
        )
    }
}