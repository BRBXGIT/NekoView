package com.example.data.remote.models.favourites_amount_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavouritesAmountResponse(
    @SerialName("list")
    val list: List<Item0> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)