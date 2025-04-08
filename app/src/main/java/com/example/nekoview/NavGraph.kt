package com.example.nekoview

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.navbar_screens.featured_screen.navigation.featuredScreen
import com.example.navbar_screens.home_screen.navigation.HomeScreenRoute
import com.example.navbar_screens.home_screen.navigation.homeScreen
import com.example.navbar_screens.settings_screen.navigation.settingsScreen

@Composable
fun NavGraph(
    bigScreen: Boolean
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute
    ) {
        homeScreen(
            navController = navController,
            bigScreen = bigScreen
        )

        featuredScreen(
            navController = navController,
            bigScreen = bigScreen
        )

        settingsScreen(
            navController = navController,
            bigScreen = bigScreen
        )
    }
}