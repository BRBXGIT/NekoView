package com.example.anime_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.anime_screen.common.AnimeScreenVM
import com.example.anime_screen.player_screen.screen.PlayerScreen
import kotlinx.serialization.Serializable

@Serializable
data class PlayerScreenRoute(
    val selectedEpisodeIndex: Int
)

fun NavGraphBuilder.playerScreenRoute(
    navController: NavController,
    animeScreenVM: AnimeScreenVM
) = composable<PlayerScreenRoute> {
    val index = it.toRoute<PlayerScreenRoute>().selectedEpisodeIndex

    PlayerScreen(
        viewModel = animeScreenVM,
        selectedEpisodeIndex = index,
        navController = navController
    )
}