package com.example.anime_screen.player_screen.screen

sealed class PlayerScreenIntent {
    data class PlayEpisode(val episodeLink: String): PlayerScreenIntent()
    data class UpdateScreenState(val state: PlayerScreenState): PlayerScreenIntent()
}