package com.example.anime_screen.anime_screen.screen

sealed class AnimeScreenIntent {
    data class FetchTitleDetails(val id: Int): AnimeScreenIntent()
    data class RetryTitleDetails(val id: Int): AnimeScreenIntent()
    //Cause vm is used in two screens it need to be initialized upper than AnimeScreen,
    //and state of title simply doesn't update, that's why i need to reset it
    data object ResetScreenState: AnimeScreenIntent()
    data class SetUserSessionToken(val token: String): AnimeScreenIntent()
    data class AddTitleToFavorites(val id: Int): AnimeScreenIntent()
}