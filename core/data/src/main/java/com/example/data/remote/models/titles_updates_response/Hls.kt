package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hls(
    @SerialName("fhd")
    val fhd: String = "",
    @SerialName("hd")
    val hd: String = "",
    @SerialName("sd")
    val sd: String = ""
)