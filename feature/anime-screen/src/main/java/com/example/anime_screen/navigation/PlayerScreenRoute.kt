package com.example.anime_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.anime_screen.player_screen.screen.PlayerScreen
import kotlinx.serialization.Serializable

@Serializable
data class PlayerScreenRoute(
    val videoUrl: String
)

fun NavGraphBuilder.playerScreen(
    navController: NavController
) = composable<PlayerScreenRoute> {
    val videoUrl = it.toRoute<PlayerScreenRoute>().videoUrl

    PlayerScreen(
        navController = navController,
        videoUrl = videoUrl
    )
}

