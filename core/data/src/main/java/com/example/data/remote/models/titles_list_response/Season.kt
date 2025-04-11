package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Season(
    @SerialName("code")
    val code: Int = 0,
    @SerialName("string")
    val string: String = "",
    @SerialName("week_day")
    val weekDay: Int = 0,
    @SerialName("year")
    val year: Int = 0
)