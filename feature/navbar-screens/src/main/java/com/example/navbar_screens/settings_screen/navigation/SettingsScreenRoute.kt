package com.example.navbar_screens.settings_screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navbar_screens.settings_screen.screen.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data object SettingsScreenRoute

fun NavGraphBuilder.settingsScreen(
    navController: NavController,
    bigScreen: Boolean
) = composable<SettingsScreenRoute>(
    enterTransition = { fadeIn(tween(300)) },
    exitTransition = { fadeOut(tween(300)) }
) {
    SettingsScreen(
        navController = navController,
        bigScreen = bigScreen
    )
}