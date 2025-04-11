package com.example.navbar_screens.home_screen.screen

sealed class HomeScreenIntent {
    data object LoadTitles: HomeScreenIntent()
    data object RetryFetch: HomeScreenIntent()
}