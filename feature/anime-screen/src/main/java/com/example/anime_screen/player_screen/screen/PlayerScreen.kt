package com.example.anime_screen.player_screen.screen

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
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
import com.example.anime_screen.player_screen.sections.EpisodesDialog
import com.example.anime_screen.player_screen.sections.PlayPauseSkipSection
import com.example.anime_screen.player_screen.sections.PlayerBottomBar
import com.example.anime_screen.player_screen.sections.PlayerTopBar
import com.example.design_system.theme.mColors
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun PlayerScreen(
    viewModel: AnimeScreenVM,
    selectedEpisodeIndex: Int,
    navController: NavController
) {
    val context = LocalContext.current
    val animeScreenState by viewModel.animeScreenState.collectAsStateWithLifecycle()
    val title = animeScreenState.title

    var index by rememberSaveable { mutableIntStateOf(selectedEpisodeIndex) }
    var isPlaying by rememberSaveable { mutableStateOf(true) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    //Top bar info
    var episodeTitle by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(index) {
        episodeTitle = if(title.player.list.values.toList()[index].name != null) {
            "${title.player.list.keys.toList()[index]} · ${title.player.list.values.toList()[index].name}"
        } else {
            "${title.player.list.keys.toList()[index]} · Кажется названия ещё нет :)"
        }

        val episode = title.player.list.values.toList()[index]
        val selectedEpisodeLink = "https://${title.player.host}${episode.hls.fhd}"
        val mediaItem = MediaItem.fromUri(selectedEpisodeLink)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        if(isPlaying) {
            exoPlayer.play()
        }
    }

    //Bottom bar info
    var currentPosition by rememberSaveable { mutableLongStateOf(0L) }
    var duration by rememberSaveable { mutableLongStateOf(0L) }

    LaunchedEffect(exoPlayer) {
        while (true) {
            currentPosition = exoPlayer.currentPosition
            duration = exoPlayer.duration
            delay(1000L)
        }
    }

    DisposableEffect(Unit) {
        val activity = context as? Activity
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            exoPlayer.release()
        }
    }

    var episodesNamesList = mutableListOf<String>()
    title.player.list.values.forEach {
        episodesNamesList.add(if(it.name != null) it.name!! else "Кажется названия ещё нет :)")
    }

    var episodesDialogOpen by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            PlayerBottomBar(
                episodeTime = formatTime(currentPosition) + " / " + formatTime(duration)
            )
        },
        topBar = {
            PlayerTopBar(
                onBackClick = { navController.navigateUp() },
                episodeTitle = episodeTitle,
                onMenuClick = { episodesDialogOpen = true }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(mColors.background)
        ) {
            if(episodesDialogOpen) {
                EpisodesDialog(
                    onDismissRequest = { episodesDialogOpen = false },
                    currentEpisodeIndex = index,
                    episodes = episodesNamesList,
                    onConfirmClick = { index = it }
                )
            }

            AnimePlayer(exoPlayer)

            PlayPauseSkipSection(
                size = animeScreenState.title.player.list.values.size,
                index = index,
                isPlaying = isPlaying,
                onPlayClick = {
                    isPlaying = !isPlaying
                    if (isPlaying) {
                        exoPlayer.play()
                    } else {
                        exoPlayer.pause()
                    }
                },
                onPreviousClick = {
                    if (index > 0) index--
                },
                onNextClick = {
                    if (index + 1 < animeScreenState.title.player.list.values.size) index++
                }
            )
        }
    }
}

private fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}