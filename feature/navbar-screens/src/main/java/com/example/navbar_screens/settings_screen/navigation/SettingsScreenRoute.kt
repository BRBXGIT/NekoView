package com.example.navbar_screens.settings_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navbar_screens.settings_screen.screen.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data object SettingsScreenRoute

fun NavGraphBuilder.settingsScreen(
    navController: NavController
) = composable<SettingsScreenRoute> {
    SettingsScreen(navController)
}