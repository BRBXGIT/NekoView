package com.example.navbar_screens.home_screen.screen

sealed class HomeScreenIntent {
    data object ChangeSearchingMode: HomeScreenIntent()
    data class SetQuery(val query: String): HomeScreenIntent()
}