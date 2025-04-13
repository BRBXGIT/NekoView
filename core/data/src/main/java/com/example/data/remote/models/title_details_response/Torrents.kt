package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List

@Serializable
data class Torrents(
    @SerialName("episodes")
    val episodes: Episodes = Episodes(),
    @SerialName("list")
    val list: List<Item0> = listOf()
)