package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    @SerialName("code")
    val code: Int = 0,
    @SerialName("string")
    val string: String = ""
)