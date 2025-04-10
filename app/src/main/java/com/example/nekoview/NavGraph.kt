package com.example.nekoview

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.common.CommonVM
import com.example.navbar_screens.featured_screen.navigation.featuredScreen
import com.example.navbar_screens.home_screen.navigation.HomeScreenRoute
import com.example.navbar_screens.home_screen.navigation.homeScreen
import com.example.navbar_screens.home_screen.screen.HomeScreenVM
import com.example.navbar_screens.settings_screen.navigation.settingsScreen

@Composable
fun NavGraph(
    bigScreen: Boolean
) {
    val navController = rememberNavController()

    //Initialize values here to don't avoid recompositions
    val homeScreenVM = hiltViewModel<HomeScreenVM>()
    val commonVM = viewModel<CommonVM>()

    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute
    ) {
        homeScreen(
            navController = navController,
            bigScreen = bigScreen,
            homeScreenVM = homeScreenVM,
            commonVM = commonVM
        )

        featuredScreen(
            navController = navController,
            bigScreen = bigScreen,
            commonVM = commonVM
        )

        settingsScreen(
            navController = navController,
            bigScreen = bigScreen,
            commonVM = commonVM
        )
    }
}