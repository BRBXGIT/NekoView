package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamesX(
    @SerialName("alternative")
    val alternative: String? = "",
    @SerialName("en")
    val en: String = "",
    @SerialName("ru")
    val ru: String = ""
)