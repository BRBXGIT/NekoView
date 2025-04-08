package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List

@Serializable
data class Torrents(
    @SerialName("episodes")
    val episodes: Episodes = Episodes(),
    @SerialName("list")
    val list: List<Item1X> = listOf()
)