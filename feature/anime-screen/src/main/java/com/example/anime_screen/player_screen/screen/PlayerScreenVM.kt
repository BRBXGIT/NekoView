package com.example.anime_screen.player_screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.common.dispatchers.Dispatcher
import com.example.common.dispatchers.NekoViewDispatchers
import com.example.data.domain.CommonRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerScreenVM @Inject constructor(
    val player: ExoPlayer,
    val commonRepo: CommonRepo,
    @Dispatcher(NekoViewDispatchers.IO) private val dispatcherIo: CoroutineDispatcher
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

    private fun rewindEpisode(position: Long) {
        player.seekTo(position)
    }

    private fun playPauseEpisode() {
        if(_playerScreenState.value.isPlaying) {
            _playerScreenState.value = _playerScreenState.value.copy(isPlaying = false)
            player.pause()
        } else {
            _playerScreenState.value = _playerScreenState.value.copy(isPlaying = true)
            player.play()
        }
    }

    private fun changeVideoQuality() {
        viewModelScope.launch(dispatcherIo) {
            commonRepo.getVideoQuality().collect {
                _playerScreenState.value = _playerScreenState.value.copy(videoQuality = it)
            }
        }
    }

    fun sendIntent(intent: PlayerScreenIntent) {
        when(intent) {
            is PlayerScreenIntent.PlayEpisode -> playEpisode(intent.episodeLink)
            is PlayerScreenIntent.UpdateScreenState -> updateScreenState(intent.state)
            is PlayerScreenIntent.RewindEpisode -> rewindEpisode(intent.position)
            is PlayerScreenIntent.PlayPause -> playPauseEpisode()
            is PlayerScreenIntent.ChangeVideoQuality -> changeVideoQuality()
        }
    }

    init {
        player.prepare()
    }
}