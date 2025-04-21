package com.example.anime_screen.player_screen.screen

sealed class PlayerScreenIntent {
    data class PlayEpisode(val episodeLink: String): PlayerScreenIntent()
    data class UpdateScreenState(val state: PlayerScreenState): PlayerScreenIntent()
    data class RewindEpisode(val position: Long): PlayerScreenIntent()
    data object PlayPause: PlayerScreenIntent()
}