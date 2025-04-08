package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List

@Serializable
data class TitlesUpdatesResponse(
    @SerialName("list")
    val list: List<Item1> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)