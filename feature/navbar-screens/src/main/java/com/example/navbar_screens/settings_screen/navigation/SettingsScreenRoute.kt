package com.example.navbar_screens.settings_screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.common.CommonVM
import com.example.navbar_screens.settings_screen.screen.SettingsScreen
import com.example.navbar_screens.settings_screen.screen.SettingsScreenVM
import kotlinx.serialization.Serializable

@Serializable
data object SettingsScreenRoute

fun NavGraphBuilder.settingsScreen(
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM,
    settingsScreenVM: SettingsScreenVM
) = composable<SettingsScreenRoute>(
    enterTransition = { fadeIn(tween(400)) },
    exitTransition = { fadeOut(tween(400)) }
) {
    SettingsScreen(
        navController = navController,
        bigScreen = bigScreen,
        commonVM = commonVM,
        viewModel = settingsScreenVM
    )
}