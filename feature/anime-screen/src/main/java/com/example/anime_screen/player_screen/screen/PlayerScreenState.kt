package com.example.anime_screen.player_screen.screen

data class PlayerScreenState(
    val isPlaying: Boolean = true,
    val episodeTitle: String = "",
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val sliderPosition: Float = 0f,
    val isUserSeeking: Boolean = false,
    val isLandscape: Boolean = true,
    var showPlayerFeatures: Boolean = false,
    val isScreenLocked: Boolean = false,
    val showUnlockButton: Boolean = false,
    val isCropped: Boolean = false,
    val episodeDialogOpen: Boolean = false,
)