package com.example.support_screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.support_screen.screen.SupportScreen
import kotlinx.serialization.Serializable

@Serializable
data object SupportScreenRoute

fun NavGraphBuilder.supportScreen(
    navController: NavController
) = composable<SupportScreenRoute>(
    enterTransition = { fadeIn(tween(400)) },
    exitTransition = { fadeOut(tween(400)) }
) {
    SupportScreen(navController)
}