package com.example.project_team.navigation

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
) = composable<ProjectTeamScreenRoute> {
    val projectTeamScreenVM = hiltViewModel<ProjectTeamScreenVM>()

    ProjectTeamScreen(
        navController = navController,
        viewModel = projectTeamScreenVM
    )
}