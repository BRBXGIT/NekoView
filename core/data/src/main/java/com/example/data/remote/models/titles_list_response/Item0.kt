package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item0(
    @SerialName("genres")
    val genres: List<String> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("names")
    val names: Names = Names(),
    @SerialName("posters")
    val posters: Posters = Posters()
)