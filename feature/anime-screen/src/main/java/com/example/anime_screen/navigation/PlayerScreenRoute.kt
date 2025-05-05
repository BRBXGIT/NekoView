package com.example.anime_screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.anime_screen.common.SharedAnimePlayerScreenVM
import com.example.anime_screen.player_screen.screen.PlayerScreen
import com.example.anime_screen.player_screen.screen.PlayerScreenVM
import com.example.common.CommonVM
import kotlinx.serialization.Serializable

@Serializable
data class PlayerScreenRoute(
    val selectedEpisodeIndex: Int
)

fun NavGraphBuilder.playerScreenRoute(
    navController: NavController,
    sharedAnimePlayerScreenVM: SharedAnimePlayerScreenVM,
    commonVM: CommonVM
) = composable<PlayerScreenRoute>(
    enterTransition = { fadeIn(tween(400)) },
    exitTransition = { fadeOut(tween(400)) }
) {
    val index = it.toRoute<PlayerScreenRoute>().selectedEpisodeIndex
    val playerScreenVM = hiltViewModel<PlayerScreenVM>()

    PlayerScreen(
        sharedViewModel = sharedAnimePlayerScreenVM,
        selectedEpisodeIndex = index,
        navController = navController,
        viewModel = playerScreenVM,
        commonVM = commonVM
    )
}