package com.example.anime_screen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.anime_screen.common.SharedAnimePlayerScreenVM
import com.example.anime_screen.player_screen.screen.PlayerScreen
import com.example.anime_screen.player_screen.screen.PlayerScreenVM
import kotlinx.serialization.Serializable

@Serializable
data class PlayerScreenRoute(
    val selectedEpisodeIndex: Int
)

fun NavGraphBuilder.playerScreenRoute(
    navController: NavController,
    sharedAnimePlayerScreenVM: SharedAnimePlayerScreenVM
) = composable<PlayerScreenRoute> {
    val index = it.toRoute<PlayerScreenRoute>().selectedEpisodeIndex
    val playerScreenVM = hiltViewModel<PlayerScreenVM>()

    PlayerScreen(
        sharedViewModel = sharedAnimePlayerScreenVM,
        selectedEpisodeIndex = index,
        navController = navController,
        viewModel = playerScreenVM
    )
}