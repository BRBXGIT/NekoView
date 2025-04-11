package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamesX(
    @SerialName("alternative")
    val alternative: String? = null,
    @SerialName("en")
    val en: String = "",
    @SerialName("ru")
    val ru: String = ""
)