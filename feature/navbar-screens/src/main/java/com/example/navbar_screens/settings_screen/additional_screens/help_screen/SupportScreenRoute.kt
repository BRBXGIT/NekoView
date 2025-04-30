package com.example.navbar_screens.settings_screen.additional_screens.help_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SupportScreenRoute

fun NavGraphBuilder.supportScreen(
    navController: NavController
) = composable<SupportScreenRoute> {
    SupportScreen(navController)
}