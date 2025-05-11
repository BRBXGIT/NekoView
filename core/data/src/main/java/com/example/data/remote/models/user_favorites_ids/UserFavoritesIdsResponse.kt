package com.example.data.remote.models.user_favorites_ids


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFavoritesIdsResponse(
    @SerialName("list")
    val list: List<Item0> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)