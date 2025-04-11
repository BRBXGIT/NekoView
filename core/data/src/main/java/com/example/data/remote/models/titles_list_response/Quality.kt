package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quality(
    @SerialName("encoder")
    val encoder: String = "",
    @SerialName("lq_audio")
    val lqAudio: String? = null,
    @SerialName("resolution")
    val resolution: String = "",
    @SerialName("string")
    val string: String = "",
    @SerialName("type")
    val type: String = ""
)