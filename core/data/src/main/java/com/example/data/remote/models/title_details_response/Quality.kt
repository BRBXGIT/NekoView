package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quality(
    @SerialName("encoder")
    val encoder: String = "",
    @SerialName("lq_audio")
    val lqAudio: String? = "",
    @SerialName("resolution")
    val resolution: String = "",
    @SerialName("string")
    val string: String = "",
    @SerialName("type")
    val type: String = ""
)