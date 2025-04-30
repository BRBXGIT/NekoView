package com.example.support_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.support_screen.screen.SupportScreen
import kotlinx.serialization.Serializable

@Serializable
data object SupportScreenRoute

fun NavGraphBuilder.supportScreen(
    navController: NavController
) = composable<SupportScreenRoute> {
    SupportScreen(navController)
}