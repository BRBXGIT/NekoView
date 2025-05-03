package com.example.navbar_screens.search_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.common.CommonVM
import com.example.navbar_screens.search_screen.screen.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object SearchScreenRoute

fun NavGraphBuilder.searchScreen(
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM
) = composable<SearchScreenRoute> {
    SearchScreen(
        navController = navController,
        bigScreen = bigScreen,
        commonVM = commonVM
    )
}