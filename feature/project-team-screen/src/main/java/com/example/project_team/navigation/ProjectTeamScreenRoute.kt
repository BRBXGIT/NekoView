package com.example.project_team.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.project_team.screen.ProjectTeamScreen
import com.example.project_team.screen.ProjectTeamScreenVM
import kotlinx.serialization.Serializable

@Serializable
data object ProjectTeamScreenRoute

fun NavGraphBuilder.projectTeamScreen(
    navController: NavController
) = composable<ProjectTeamScreenRoute>(
    enterTransition = { fadeIn(tween(400)) },
    exitTransition = { fadeOut(tween(400)) }
) {
    val projectTeamScreenVM = hiltViewModel<ProjectTeamScreenVM>()

    ProjectTeamScreen(
        navController = navController,
        viewModel = projectTeamScreenVM
    )
}