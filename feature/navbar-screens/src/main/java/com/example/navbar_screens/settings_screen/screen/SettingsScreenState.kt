package com.example.navbar_screens.settings_screen.screen

data class SettingsScreenState(
    val userName: String = "",
    val userImageUrl: String = "",
    val userDetailsLoading: Boolean = true
)