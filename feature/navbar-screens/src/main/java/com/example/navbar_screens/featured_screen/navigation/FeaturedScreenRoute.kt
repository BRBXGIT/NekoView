package com.example.navbar_screens.featured_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navbar_screens.featured_screen.screen.FeaturedScreen
import kotlinx.serialization.Serializable

@Serializable
data object FeaturedScreenRoute

fun NavGraphBuilder.featuredScreen(
    navController: NavController
) = composable<FeaturedScreenRoute> {
    FeaturedScreen(navController)
}