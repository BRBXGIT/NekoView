package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Small(
    @SerialName("raw_base64_file")
    val rawBase64File: String? = "",
    @SerialName("url")
    val url: String = ""
)