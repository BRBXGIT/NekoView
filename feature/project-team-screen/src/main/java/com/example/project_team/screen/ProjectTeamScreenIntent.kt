package com.example.project_team.screen

sealed class ProjectTeamScreenIntent {
    data object FetchProjectTeam: ProjectTeamScreenIntent()
    data object RetryFetchProjectTeam: ProjectTeamScreenIntent()
}