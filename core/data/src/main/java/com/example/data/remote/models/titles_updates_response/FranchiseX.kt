package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FranchiseX(
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = ""
)