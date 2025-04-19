package com.example.anime_screen.player_screen.screen

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import com.example.anime_screen.common.AnimeScreenVM
import com.example.anime_screen.player_screen.sections.AnimePlayer
import com.example.anime_screen.player_screen.sections.PlayPauseSkipSection
import com.example.design_system.theme.mColors

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun PlayerScreen(
    viewModel: AnimeScreenVM,
    selectedEpisodeIndex: Int,
    navController: NavController
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
    ) {
        val animeScreenState by viewModel.animeScreenState.collectAsStateWithLifecycle()
        var index by rememberSaveable { mutableIntStateOf(selectedEpisodeIndex) }

        var selectedEpisodeLink = "https://" +
                animeScreenState.title.player.host +
                animeScreenState.title.player.list.values.toList()[index].hls.fhd

        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(selectedEpisodeLink)
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            }
        }

        AnimePlayer(exoPlayer)

        var isPlaying by rememberSaveable { mutableStateOf(true) }
        PlayPauseSkipSection(
            size = animeScreenState.title.player.list.values.size,
            index = index,
            isPlaying = isPlaying,
            onPlayClick = {
                when (isPlaying) {
                    true -> {
                        isPlaying = false
                        exoPlayer.pause()
                    }
                    false -> {
                        isPlaying = true
                        exoPlayer.play()
                    }
                }
            },
            onPreviousClick = {
                index -= 1
            },
            onNextClick = {
                index += 1
            },
        )
    }
}