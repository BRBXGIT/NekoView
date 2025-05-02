package com.example.tuning_screen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tuning_screen.screen.TuningScreen
import com.example.tuning_screen.screen.TuningScreenVM
import kotlinx.serialization.Serializable

@Serializable
data object TuningScreenRoute

fun NavGraphBuilder.tuningScreen(
    navController: NavController
) = composable<TuningScreenRoute> {
    val tuningScreenVM = hiltViewModel<TuningScreenVM>()

    TuningScreen(
        navController = navController,
        viewModel = tuningScreenVM
    )
}