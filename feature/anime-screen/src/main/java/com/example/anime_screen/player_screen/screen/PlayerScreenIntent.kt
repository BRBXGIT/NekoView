package com.example.anime_screen.player_screen.screen

sealed class PlayerScreenIntent {
    data class PlayEpisode(val episodeLink: String): PlayerScreenIntent()
    data class UpdateScreenState(val state: PlayerScreenState): PlayerScreenIntent()
    data class RewindEpisode(val position: Long): PlayerScreenIntent()
    data object PlayPause: PlayerScreenIntent()
    data class ChangeVideoQuality(val quality: Int): PlayerScreenIntent()
    data object FetchVideoQuality: PlayerScreenIntent()
    data object FetchShowSkipOpeningButton: PlayerScreenIntent()
    data object ChangeShowSkipOpeningButton: PlayerScreenIntent()
    data object FetchAutoSkipOpening: PlayerScreenIntent()
    data object ChangeAutoSkipOpening: PlayerScreenIntent()
}