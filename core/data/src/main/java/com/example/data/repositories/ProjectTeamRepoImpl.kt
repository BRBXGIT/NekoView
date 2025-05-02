package com.example.data.repositories

import com.example.data.domain.ProjectTeamScreenRepo
import com.example.data.remote.ktor.ProjectTeamScreenKtorClient
import com.example.data.remote.models.project_team_response.ProjectTeamResponse
import com.example.data.remote.utils.NetworkError
import com.example.data.remote.utils.Result
import javax.inject.Inject

class ProjectTeamRepoImpl @Inject constructor(
    private val ktorClient: ProjectTeamScreenKtorClient
): ProjectTeamScreenRepo {

    override suspend fun getProjectTeam(): Result<ProjectTeamResponse, NetworkError> {
        return ktorClient.getProjectTeam()
    }
}