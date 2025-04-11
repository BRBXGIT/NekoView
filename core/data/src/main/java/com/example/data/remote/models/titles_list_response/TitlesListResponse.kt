package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List

@Serializable
data class TitlesListResponse(
    @SerialName("list")
    val list: List<Item1> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)