package com.example.nekoview

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.anime_screen.common.SharedAnimePlayerScreenVM
import com.example.anime_screen.navigation.animeScreen
import com.example.anime_screen.navigation.playerScreenRoute
import com.example.common.CommonVM
import com.example.design_system.theme.AppThemeVM
import com.example.navbar_screens.featured_screen.navigation.featuredScreen
import com.example.navbar_screens.featured_screen.screen.FeaturedScreenVM
import com.example.navbar_screens.home_screen.navigation.HomeScreenRoute
import com.example.navbar_screens.home_screen.navigation.homeScreen
import com.example.navbar_screens.home_screen.screen.HomeScreenVM
import com.example.navbar_screens.search_screen.navigation.searchScreen
import com.example.navbar_screens.search_screen.screen.SearchScreenVM
import com.example.navbar_screens.settings_screen.navigation.settingsScreen
import com.example.navbar_screens.settings_screen.screen.SettingsScreenVM
import com.example.project_team.navigation.projectTeamScreen
import com.example.support_screen.navigation.supportScreen
import com.example.tuning_screen.navigation.tuningScreen

@Composable
fun NavGraph(
    bigScreen: Boolean,
    appThemeVM: AppThemeVM
) {
    val navController = rememberNavController()

    //Initialize values here to avoid recompositions
    val commonVM = hiltViewModel<CommonVM>()
    val homeScreenVM = hiltViewModel<HomeScreenVM>()
    val featuredScreenVM = hiltViewModel<FeaturedScreenVM>()
    val settingsScreenVM = hiltViewModel<SettingsScreenVM>()
    val searchScreenVM = hiltViewModel<SearchScreenVM>()
    val sharedAnimePlayerScreenVM = hiltViewModel<SharedAnimePlayerScreenVM>()

    //Collect here to don't fetch every time user comes on screen
    val titlesByFilters = searchScreenVM.titlesByAdvancedQuery.collectAsLazyPagingItems()

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

        searchScreen(
            navController = navController,
            bigScreen = bigScreen,
            commonVM = commonVM,
            searchScreenVM = searchScreenVM,
            titlesByFilters = titlesByFilters
        )

        settingsScreen(
            navController = navController,
            bigScreen = bigScreen,
            commonVM = commonVM,
            settingsScreenVM = settingsScreenVM
        )

        animeScreen(
            navController = navController,
            sharedAnimePlayerScreenVM = sharedAnimePlayerScreenVM,
            commonVM = commonVM
        )

        playerScreenRoute(
            navController = navController,
            sharedAnimePlayerScreenVM = sharedAnimePlayerScreenVM,
            commonVM = commonVM
        )

        supportScreen(navController)

        projectTeamScreen(navController)

        tuningScreen(
            navController = navController,
            appThemeVM = appThemeVM,
            commonVM = commonVM
        )
    }
}