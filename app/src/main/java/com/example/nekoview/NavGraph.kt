package com.example.nekoview

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.anime_screen.common.SharedAnimePlayerScreenVM
import com.example.anime_screen.navigation.animeScreen
import com.example.anime_screen.navigation.playerScreenRoute
import com.example.common.CommonVM
import com.example.navbar_screens.featured_screen.navigation.featuredScreen
import com.example.navbar_screens.featured_screen.screen.FeaturedScreenVM
import com.example.navbar_screens.home_screen.navigation.HomeScreenRoute
import com.example.navbar_screens.home_screen.navigation.homeScreen
import com.example.navbar_screens.home_screen.screen.HomeScreenVM
import com.example.navbar_screens.settings_screen.additional_screens.help_screen.supportScreen
import com.example.navbar_screens.settings_screen.navigation.settingsScreen
import com.example.navbar_screens.settings_screen.screen.SettingsScreenVM

@Composable
fun NavGraph(
    bigScreen: Boolean
) {
    val navController = rememberNavController()

    //Initialize values here to avoid recompositions
    val commonVM = hiltViewModel<CommonVM>()
    val homeScreenVM = hiltViewModel<HomeScreenVM>()
    val featuredScreenVM = hiltViewModel<FeaturedScreenVM>()
    val settingsScreenVM = hiltViewModel<SettingsScreenVM>()
    val sharedAnimePlayerScreenVM = hiltViewModel<SharedAnimePlayerScreenVM>()

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
            commonVM = commonVM,
            featuredScreenVM = featuredScreenVM
        )

        settingsScreen(
            navController = navController,
            bigScreen = bigScreen,
            commonVM = commonVM,
            settingsScreenVM = settingsScreenVM
        )

        animeScreen(
            navController = navController,
            sharedAnimePlayerScreenVM = sharedAnimePlayerScreenVM
        )

        playerScreenRoute(
            navController = navController,
            sharedAnimePlayerScreenVM = sharedAnimePlayerScreenVM
        )

        supportScreen(navController)
    }
}