package com.example.tuning_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.design_system.theme.AppThemeVM
import com.example.tuning_screen.screen.TuningScreen
import kotlinx.serialization.Serializable

@Serializable
data object TuningScreenRoute

fun NavGraphBuilder.tuningScreen(
    navController: NavController,
    appThemeVM: AppThemeVM
) = composable<TuningScreenRoute> {

    TuningScreen(
        navController = navController,
        appThemeVM = appThemeVM
    )
}