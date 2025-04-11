package com.example.navbar_screens.featured_screen.screen

sealed class FeatureScreenIntent {
    data class LoadUserFeaturedTitles(val sessionToken: String): FeatureScreenIntent()
    data class RetryFetch(val sessionToken: String): FeatureScreenIntent()
}