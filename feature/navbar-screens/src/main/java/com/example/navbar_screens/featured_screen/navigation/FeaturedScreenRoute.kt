package com.example.navbar_screens.featured_screen.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.common.CommonVM
import com.example.navbar_screens.featured_screen.screen.FeaturedScreen
import com.example.navbar_screens.featured_screen.screen.FeaturedScreenVM
import kotlinx.serialization.Serializable

@Serializable
data object FeaturedScreenRoute

fun NavGraphBuilder.featuredScreen(
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM,
    featuredScreenVM: FeaturedScreenVM
) = composable<FeaturedScreenRoute>(
    enterTransition = { fadeIn(tween(300)) },
    exitTransition = { fadeOut(tween(300)) }
) {
    FeaturedScreen(
        navController = navController,
        bigScreen = bigScreen,
        commonVM = commonVM,
        viewModel = featuredScreenVM
    )
}