package com.example.navbar_screens.youtube_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navbar_screens.youtube_screen.screen.YoutubeScreen
import kotlinx.serialization.Serializable

@Serializable
data object YoutubeScreenRoute

fun NavGraphBuilder.youtubeScreen(
    navController: NavController
) = composable<YoutubeScreenRoute> {
    YoutubeScreen(navController)
}