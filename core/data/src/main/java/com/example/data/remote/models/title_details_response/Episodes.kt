package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Episodes(
    @SerialName("first")
    val first: Int = 0,
    @SerialName("last")
    val last: Int = 0,
    @SerialName("string")
    val string: String = ""
)