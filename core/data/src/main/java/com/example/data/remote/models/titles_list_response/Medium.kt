package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Medium(
    @SerialName("raw_base64_file")
    val rawBase64File: String? = null,
    @SerialName("url")
    val url: String = ""
)