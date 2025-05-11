package com.example.data.remote.models.put_title_to_favorites_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PutDeleteTitleToFavoritesResponse(
    @SerialName("success")
    val success: Boolean = false
)