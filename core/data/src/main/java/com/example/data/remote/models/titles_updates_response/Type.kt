package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("code")
    val code: Int = 0,
    @SerialName("episodes")
    val episodes: Int? = null,
    @SerialName("full_string")
    val fullString: String = "",
    @SerialName("length")
    val length: Int? = null,
    @SerialName("string")
    val string: String = ""
)