package com.example.anime_screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.anime_screen.anime_screen.screen.AnimeScreen
import com.example.anime_screen.common.SharedAnimePlayerScreenVM
import kotlinx.serialization.Serializable

@Serializable
data class AnimeScreenRoute(
    val titleId: Int
)

fun NavGraphBuilder.animeScreen(
    navController: NavController,
    sharedAnimePlayerScreenVM: SharedAnimePlayerScreenVM
) = composable<AnimeScreenRoute>(
    enterTransition = { fadeIn(tween(400)) },
    exitTransition = { fadeOut(tween(400)) }
) {
    val titleId = it.toRoute<AnimeScreenRoute>().titleId

    AnimeScreen(
        navController = navController,
        titleId = titleId,
        viewModel = sharedAnimePlayerScreenVM
    )
}