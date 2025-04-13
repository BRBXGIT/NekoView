package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Release(
    @SerialName("code")
    val code: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("names")
    val names: Names = Names(),
    @SerialName("ordinal")
    val ordinal: Int = 0
)