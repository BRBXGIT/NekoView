package com.example.data.remote.models.project_team_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectTeamResponse(
    @SerialName("decor")
    val decor: List<String> = listOf(),
    @SerialName("editing")
    val editing: List<String> = listOf(),
    @SerialName("timing")
    val timing: List<String> = listOf(),
    @SerialName("translator")
    val translator: List<String> = listOf(),
    @SerialName("voice")
    val voice: List<String> = listOf()
)