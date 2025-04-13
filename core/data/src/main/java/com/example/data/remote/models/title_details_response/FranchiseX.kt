package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FranchiseX(
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = ""
)