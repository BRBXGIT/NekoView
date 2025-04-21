package com.example.anime_screen.player_screen.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import com.example.anime_screen.common.AnimeScreenVM
import com.example.anime_screen.player_screen.sections.AnimePlayer
import com.example.anime_screen.player_screen.sections.Direction
import com.example.anime_screen.player_screen.sections.EpisodesDialog
import com.example.anime_screen.player_screen.sections.PlayPauseSkipSection
import com.example.anime_screen.player_screen.sections.PlayerFeaturesBottomBar
import com.example.anime_screen.player_screen.sections.PlayerTopBar
import com.example.anime_screen.player_screen.sections.PlayerUnlockButtonBottomBar
import com.example.anime_screen.player_screen.sections.PlusSecondsBox
import com.example.design_system.custom_modifiers.noRippleClickable
import com.example.design_system.theme.mColors
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationGraphicsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerScreen(
    viewModel: AnimeScreenVM,
    selectedEpisodeIndex: Int,
    navController: NavController
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val animeScreenState by viewModel.animeScreenState.collectAsStateWithLifecycle()
    val title = animeScreenState.title

    var episodeIndex by rememberSaveable { mutableIntStateOf(selectedEpisodeIndex) }
    var isPlaying by rememberSaveable { mutableStateOf(true) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    //Top bar info
    var episodeTitle by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(episodeIndex) {
        episodeTitle = if(title.player.list.values.toList()[episodeIndex].name != null) {
            "${title.player.list.keys.toList()[episodeIndex]} · ${title.player.list.values.toList()[episodeIndex].name}"
        } else {
            "${title.player.list.keys.toList()[episodeIndex]} · Кажется названия ещё нет :)"
        }

        val episodeLinks = title.player.list.values.toList()[episodeIndex]
        val selectedEpisodeLink = "https://${title.player.host}${episodeLinks.hls.fhd}"
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
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var isUserSeeking by remember { mutableStateOf(false) }
    LaunchedEffect(exoPlayer) {
        while(true) {
            if(!isUserSeeking) {
                currentPosition = exoPlayer.currentPosition
                duration = exoPlayer.duration
                sliderPosition = if (duration > 0) currentPosition.toFloat() / duration else 0f
                delay(1000L)
            } else {
                currentPosition = (duration * sliderPosition).toLong()
                delay(10L)
            }
        }
    }

    //TODO change screen orientation when user navigates back with native android
    var isLandscape by rememberSaveable { mutableStateOf(true) }
    LaunchedEffect(isLandscape) {
        if(isLandscape) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    var episodesDialogOpen by rememberSaveable { mutableStateOf(false) }
    var showPlayerFeatures by rememberSaveable { mutableStateOf(false) }
    var isScreenLocked by rememberSaveable { mutableStateOf(false) }

    var showUnlockButton by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(showUnlockButton) {
        if(showUnlockButton) {
            delay(3000)
            showUnlockButton = false
        }
    }

    var isCropped by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            if(isScreenLocked) {
                PlayerUnlockButtonBottomBar(
                    showUnlockButton = showUnlockButton,
                    onClick = {
                        isScreenLocked = false
                        showUnlockButton = false
                    }
                )
            } else {
                PlayerFeaturesBottomBar(
                    showPlayerFeatures = showPlayerFeatures,
                    episodeTime = formatTime(currentPosition) + " / " + formatTime(duration),
                    onQuitFullScreenClick = {
                        if(isLandscape) {
                            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                            isLandscape = false
                        } else {
                            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            isLandscape = true
                        }
                    },
                    onLockScreenClick = {
                        isScreenLocked = true
                        showPlayerFeatures = false
                        showUnlockButton = true
                    },
                    onFillScreenClick = { isCropped = !isCropped },
                    isCropped = isCropped,
                    isLandscape = isLandscape,
                    sliderPosition = sliderPosition,
                    onSliderValueChange = {
                        isUserSeeking = true
                        sliderPosition = it
                    },
                    onSliderValueChangeFinished = {
                        val seekPosition = (duration * sliderPosition).toLong()
                        exoPlayer.seekTo(seekPosition)
                        currentPosition = seekPosition
                        isUserSeeking = false
                    }
                )
            }
        },
        topBar = {
            PlayerTopBar(
                onBackClick = {
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    exoPlayer.release()
                    navController.navigateUp()
                },
                episodeTitle = episodeTitle,
                onMenuClick = { episodesDialogOpen = true },
                showPlayerFeatures = showPlayerFeatures
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
            .noRippleClickable(
                onClick = {
                    if(!isScreenLocked) {
                        showPlayerFeatures = !showPlayerFeatures
                    } else {
                        showUnlockButton = true
                    }
                }
            )
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(mColors.background)
        ) {
            var episodesNamesList = mutableListOf<String>()
            title.player.list.values.forEach {
                episodesNamesList.add(if(it.name != null) it.name!! else "Кажется названия ещё нет :)")
            }
            if(episodesDialogOpen) {
                EpisodesDialog(
                    onDismissRequest = { episodesDialogOpen = false },
                    currentEpisodeIndex = episodeIndex,
                    episodes = episodesNamesList,
                    onConfirmClick = { episodeIndex = it }
                )
            }

            AnimePlayer(
                exoPlayer = exoPlayer,
                isCropped = isCropped
            )

            PlayPauseSkipSection(
                showPlayerFeatures = showPlayerFeatures,
                size = animeScreenState.title.player.list.values.size,
                index = episodeIndex,
                isPlaying = isPlaying,
                onPreviousClick = { if (episodeIndex > 0) episodeIndex-- },
                onNextClick = {
                    if (episodeIndex + 1 < animeScreenState.title.player.list.values.size) episodeIndex++
                },
                onPlayClick = {
                    isPlaying = !isPlaying
                    if(isPlaying) {
                        exoPlayer.play()
                    } else {
                        exoPlayer.pause()
                    }
                },
            )

            if(!isScreenLocked) {
                PlusSecondsBox(
                    onDoubleClick = {
                        val newPosition = (exoPlayer.currentPosition - 5000).coerceAtMost(exoPlayer.duration)
                        exoPlayer.seekTo(newPosition)
                    },
                    direction = Direction.Minus
                )

                PlusSecondsBox(
                    onDoubleClick = {
                        val newPosition = (exoPlayer.currentPosition + 5000).coerceAtMost(exoPlayer.duration)
                        exoPlayer.seekTo(newPosition)
                    },
                    direction = Direction.Plus
                )
            }

            //Cover all screen except player features
            val animatedCoverAlpha by animateFloatAsState(
                targetValue = if(showPlayerFeatures) 0.5f else 0f,
                animationSpec = tween(300),
                label = "Animated alpha for box"
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = animatedCoverAlpha
                    }
                    .background(Color.Black)
                    .zIndex(1f)
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