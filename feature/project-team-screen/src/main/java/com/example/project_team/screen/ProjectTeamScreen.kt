package com.example.project_team.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.data.remote.models.project_team_response.ProjectTeamResponse
import com.example.design_system.sections.EmptyContentSection
import com.example.design_system.theme.mColors
import com.example.project_team.sections.ProjectTeamLCSection
import com.example.project_team.sections.ProjectTeamScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectTeamScreen(
    navController: NavController,
    viewModel: ProjectTeamScreenVM
) {
    val projectTeamScreenState by viewModel.projectTeam.collectAsStateWithLifecycle()

    val topBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            ProjectTeamScreenTopBar(
                loadingState = projectTeamScreenState.isLoading,
                scrollBehavior = topBarScrollBehavior,
                onBackClick = { navController.navigateUp() }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if((projectTeamScreenState.projectTeam == ProjectTeamResponse()) and (!projectTeamScreenState.isLoading)) {
                EmptyContentSection(modifier = Modifier.fillMaxSize())
            } else {
                ProjectTeamLCSection(projectTeamScreenState.projectTeam)
            }
        }
    }
}