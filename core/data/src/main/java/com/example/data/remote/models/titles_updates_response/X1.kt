package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X1(
    @SerialName("created_timestamp")
    val createdTimestamp: Int = 0,
    @SerialName("episode")
    val episode: Int = 0,
    @SerialName("hls")
    val hls: Hls = Hls(),
    @SerialName("name")
    val name: String? = null,
    @SerialName("preview")
    val preview: String = "",
    @SerialName("skips")
    val skips: Skips = Skips(),
    @SerialName("uuid")
    val uuid: String = ""
)