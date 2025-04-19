package com.example.anime_screen.anime_screen.screen

sealed class AnimeScreenIntent {
    data class FetchTitleDetails(val id: Int): AnimeScreenIntent()
    data class RetryTitleDetails(val id: Int): AnimeScreenIntent()
}