package com.example.data.domain

import com.example.data.remote.models.project_team_response.ProjectTeamResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result

interface ProjectTeamScreenRepo {

    suspend fun getProjectTeam(): Result<ProjectTeamResponse, NetworkError>
}