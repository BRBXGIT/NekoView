package com.example.anime_screen.player_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PlayerScreenVM @Inject constructor(
    val player: ExoPlayer
): ViewModel() {

    private val _playerScreenState = MutableStateFlow(PlayerScreenState())
    val playerScreenState = _playerScreenState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        PlayerScreenState()
    )

    private fun playEpisode(episodeLink: String) {
        val mediaItem = MediaItem.fromUri(episodeLink)
        player.setMediaItem(mediaItem)
        player.prepare()
        if(playerScreenState.value.isPlaying) {
            player.play()
        }
    }

    private fun updateScreenState(state: PlayerScreenState) {
        _playerScreenState.value = state
    }

    fun sendIntent(intent: PlayerScreenIntent) {
        when(intent) {
            is PlayerScreenIntent.PlayEpisode -> playEpisode(intent.episodeLink)
            is PlayerScreenIntent.UpdateScreenState -> updateScreenState(intent.state)
        }
    }
}