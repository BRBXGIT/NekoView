package com.example.navbar_screens.settings_screen.screen

sealed class SettingsScreenIntent {
    data class LoadUserDetails(val sessionToken: String): SettingsScreenIntent()
    data class RetryUserDetails(val sessionToken: String): SettingsScreenIntent()
}