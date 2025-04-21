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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.anime_screen.common.SharedAnimePlayerScreenVM
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
    sharedViewModel: SharedAnimePlayerScreenVM,
    selectedEpisodeIndex: Int,
    navController: NavController,
    viewModel: PlayerScreenVM
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val animeScreenState by sharedViewModel.animeScreenState.collectAsStateWithLifecycle()
    val title = animeScreenState.title

    val playerScreenState by viewModel.playerScreenState.collectAsStateWithLifecycle()

    var episodeIndex by rememberSaveable { mutableIntStateOf(selectedEpisodeIndex) }

    val exoPlayer = viewModel.player.apply {
        playWhenReady = true
    }

    //Top bar info
    LaunchedEffect(episodeIndex) {
        val episodeTitle = if(title.player.list.values.toList()[episodeIndex].name != null) {
            "${title.player.list.keys.toList()[episodeIndex]} · ${title.player.list.values.toList()[episodeIndex].name}"
        } else {
            "${title.player.list.keys.toList()[episodeIndex]} · Кажется названия ещё нет :)"
        }
        viewModel.sendIntent(
            PlayerScreenIntent.UpdateScreenState(
                playerScreenState.copy(episodeTitle = episodeTitle)
            )
        )

        val episodeLinks = title.player.list.values.toList()[episodeIndex]
        val selectedEpisodeLink = "https://${title.player.host}${episodeLinks.hls.fhd}"
        viewModel.sendIntent(PlayerScreenIntent.PlayEpisode(selectedEpisodeLink))
    }

    //Bottom bar info
    LaunchedEffect(exoPlayer) {
        while(true) {
            val duration = exoPlayer.duration
            val currentPosition = exoPlayer.currentPosition

            if(!playerScreenState.isUserSeeking) {
                viewModel.sendIntent(
                    PlayerScreenIntent.UpdateScreenState(
                        playerScreenState.copy(
                            currentPosition = exoPlayer.currentPosition,
                            duration = duration,
                            sliderPosition = if(duration > 0) currentPosition.toFloat() / duration else 0f
                        )
                    )
                )
                delay(100L)
            } else {
                viewModel.sendIntent(
                    PlayerScreenIntent.UpdateScreenState(
                        playerScreenState.copy(
                            currentPosition = (duration * playerScreenState.sliderPosition).toLong(),
                        )
                    )
                )
                delay(10L)
            }
        }
    }

    //TODO change screen orientation when user navigates back with native android
    LaunchedEffect(playerScreenState.isLandscape) {
        if(playerScreenState.isLandscape) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    LaunchedEffect(playerScreenState.showUnlockButton) {
        if(playerScreenState.showUnlockButton) {
            delay(3000)
            viewModel.sendIntent(
                PlayerScreenIntent.UpdateScreenState(
                    playerScreenState.copy(
                        showUnlockButton = false
                    )
                )
            )
        }
    }

    var isCropped by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            if(playerScreenState.isScreenLocked) {
                PlayerUnlockButtonBottomBar(
                    showUnlockButton = playerScreenState.showUnlockButton,
                    onClick = {
                        viewModel.sendIntent(
                            PlayerScreenIntent.UpdateScreenState(
                                playerScreenState.copy(
                                    isScreenLocked = false,
                                    showUnlockButton = false
                                )
                            )
                        )
                    }
                )
            } else {
                PlayerFeaturesBottomBar(
                    showPlayerFeatures = playerScreenState.showPlayerFeatures,
                    episodeTime = formatTime(playerScreenState.currentPosition) + " / " + formatTime(playerScreenState.duration),
                    onQuitFullScreenClick = {
                        if(playerScreenState.isLandscape) {
                            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                            viewModel.sendIntent(
                                PlayerScreenIntent.UpdateScreenState(
                                    playerScreenState.copy(
                                        isLandscape = false
                                    )
                                )
                            )
                        } else {
                            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            viewModel.sendIntent(
                                PlayerScreenIntent.UpdateScreenState(
                                    playerScreenState.copy(
                                        isLandscape = true
                                    )
                                )
                            )
                        }
                    },
                    onLockScreenClick = {
                        viewModel.sendIntent(
                            PlayerScreenIntent.UpdateScreenState(
                                playerScreenState.copy(
                                    isScreenLocked = true,
                                    showPlayerFeatures = false,
                                    showUnlockButton = true
                                )
                            )
                        )
                    },
                    onFillScreenClick = { isCropped = !isCropped },
                    isCropped = isCropped,
                    isLandscape = playerScreenState.isLandscape,
                    sliderPosition = playerScreenState.sliderPosition,
                    onSliderValueChange = {
                        viewModel.sendIntent(
                            PlayerScreenIntent.UpdateScreenState(
                                playerScreenState.copy(
                                    isUserSeeking = true,
                                    sliderPosition = it
                                )
                            )
                        )
                    },
                    onSliderValueChangeFinished = {
                        val seekPosition = (playerScreenState.duration * playerScreenState.sliderPosition).toLong()
                        exoPlayer.seekTo(seekPosition)

                        viewModel.sendIntent(
                            PlayerScreenIntent.UpdateScreenState(
                                playerScreenState.copy(
                                    currentPosition = seekPosition,
                                    isUserSeeking = false
                                )
                            )
                        )
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
                episodeTitle = playerScreenState.episodeTitle,
                onMenuClick = {
                    viewModel.sendIntent(
                        PlayerScreenIntent.UpdateScreenState(
                            playerScreenState.copy(
                                episodeDialogOpen = true
                            )
                        )
                    )
                },
                showPlayerFeatures = playerScreenState.showPlayerFeatures
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
            .noRippleClickable(
                onClick = {
                    if(!playerScreenState.isScreenLocked) {
                        viewModel.sendIntent(
                            PlayerScreenIntent.UpdateScreenState(
                                playerScreenState.copy(
                                    showPlayerFeatures = !playerScreenState.showPlayerFeatures
                                )
                            )
                        )
                    } else {
                        viewModel.sendIntent(
                            PlayerScreenIntent.UpdateScreenState(
                                playerScreenState.copy(
                                    showUnlockButton = true
                                )
                            )
                        )
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
            if(playerScreenState.episodeDialogOpen) {
                EpisodesDialog(
                    onDismissRequest = {
                        viewModel.sendIntent(
                            PlayerScreenIntent.UpdateScreenState(
                                playerScreenState.copy(
                                    episodeDialogOpen = false
                                )
                            )
                        )
                    },
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
                showPlayerFeatures = playerScreenState.showPlayerFeatures,
                size = animeScreenState.title.player.list.values.size,
                index = episodeIndex,
                isPlaying = playerScreenState.isPlaying,
                onPreviousClick = { if (episodeIndex > 0) episodeIndex-- },
                onNextClick = {
                    if (episodeIndex + 1 < animeScreenState.title.player.list.values.size) episodeIndex++
                },
                onPlayClick = {
                    viewModel.sendIntent(
                        PlayerScreenIntent.UpdateScreenState(
                            playerScreenState.copy(isPlaying = !playerScreenState.isPlaying)
                        )
                    )
                    if(playerScreenState.isPlaying) {
                        exoPlayer.play()
                    } else {
                        exoPlayer.pause()
                    }
                },
            )

            if(!playerScreenState.isScreenLocked) {
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
                targetValue = if(playerScreenState.showPlayerFeatures) 0.5f else 0f,
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