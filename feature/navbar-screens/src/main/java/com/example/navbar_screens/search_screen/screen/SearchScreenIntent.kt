package com.example.navbar_screens.search_screen.screen

sealed class SearchScreenIntent {
    data object FetchTitlesGenres: SearchScreenIntent()
    data object RetryFetchTitlesGenres: SearchScreenIntent()
    data object RetryFetchTitlesYears: SearchScreenIntent()
    data object FetchTitleYears: SearchScreenIntent()
}