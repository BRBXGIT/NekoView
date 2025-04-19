package com.example.anime_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.anime_screen.common.AnimeScreenVM
import kotlinx.serialization.Serializable

@Serializable
data object PlayerScreenRoute

fun NavGraphBuilder.playerScreenRoute(
    navController: NavController,
    animeScreenVM: AnimeScreenVM
) = composable<PlayerScreenRoute> {

}