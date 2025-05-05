package com.example.navbar_screens.search_screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.common.CommonVM
import com.example.navbar_screens.search_screen.screen.SearchScreen
import com.example.navbar_screens.search_screen.screen.SearchScreenVM
import kotlinx.serialization.Serializable

@Serializable
data object SearchScreenRoute

fun NavGraphBuilder.searchScreen(
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM,
    searchScreenVM: SearchScreenVM
) = composable<SearchScreenRoute>(
    enterTransition = { fadeIn(tween(400)) },
    exitTransition = { fadeOut(tween(400)) }
) {
    SearchScreen(
        navController = navController,
        bigScreen = bigScreen,
        commonVM = commonVM,
        viewModel = searchScreenVM
    )
}