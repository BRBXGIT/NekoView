package com.example.anime_screen.player_screen.screen

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerScreenVM @Inject constructor(
    val player: ExoPlayer
): ViewModel() {

    init {
        player.play()
    }
}