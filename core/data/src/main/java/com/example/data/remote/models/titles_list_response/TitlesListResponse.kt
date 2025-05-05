package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitlesListResponse(
    @SerialName("list")
    val list: List<Item0> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)