package com.example.anime_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.anime_screen.screen.AnimeScreen
import com.example.data.remote.models.titles_list_response.Item1
import kotlinx.serialization.Serializable

@Serializable
data class AnimeScreenRoute(
    val title: Item1
)

fun NavGraphBuilder.animeScreen(
    navController: NavController
) = composable<AnimeScreenRoute> {
    val title = it.toRoute<AnimeScreenRoute>().title

    AnimeScreen(title)
}