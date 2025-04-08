package com.example.navbar_screens.home_screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navbar_screens.home_screen.screen.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

fun NavGraphBuilder.homeScreen(
    navController: NavController,
    bigScreen: Boolean
) = composable<HomeScreenRoute>(
    enterTransition = { fadeIn(tween(300)) },
    exitTransition = { fadeOut(tween(300)) }
) {
    HomeScreen(
        navController = navController,
        bigScreen = bigScreen
    )
}