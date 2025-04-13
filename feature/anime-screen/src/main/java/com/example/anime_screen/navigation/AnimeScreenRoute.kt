package com.example.anime_screen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.anime_screen.screen.AnimeScreen
import com.example.anime_screen.screen.AnimeScreenVM
import kotlinx.serialization.Serializable

@Serializable
data class AnimeScreenRoute(
    val titleId: Int
)

fun NavGraphBuilder.animeScreen(
    navController: NavController
) = composable<AnimeScreenRoute> {
    val titleId = it.toRoute<AnimeScreenRoute>().titleId
    val animeScreenVM = hiltViewModel<AnimeScreenVM>()

    AnimeScreen(
        navController = navController,
        titleId = titleId,
        viewModel = animeScreenVM
    )
}