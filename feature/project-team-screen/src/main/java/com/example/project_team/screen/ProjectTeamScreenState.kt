package com.example.project_team.screen

import com.example.data.remote.models.project_team_response.ProjectTeamResponse

data class ProjectTeamScreenState(
    val projectTeam: ProjectTeamResponse = ProjectTeamResponse(),
    val isLoading: Boolean = true,
)
