package com.example.navbar_screens.home_screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.common.CommonVM
import com.example.navbar_screens.home_screen.screen.HomeScreen
import com.example.navbar_screens.home_screen.screen.HomeScreenVM
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

fun NavGraphBuilder.homeScreen(
    homeScreenVM: HomeScreenVM,
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM
) = composable<HomeScreenRoute>(
    enterTransition = { fadeIn(tween(300)) },
    exitTransition = { fadeOut(tween(300)) }
) {
    HomeScreen(
        navController = navController,
        bigScreen = bigScreen,
        viewModel = homeScreenVM,
        commonVM = commonVM
    )
}