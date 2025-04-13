package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    @SerialName("alternative_player")
    val alternativePlayer: String? = "",
    @SerialName("episodes")
    val episodes: Episodes = Episodes(),
    @SerialName("host")
    val host: String = "",
    @SerialName("is_rutube")
    val isRutube: Boolean = false,
    @SerialName("list")
    val list: List = List(),
    @SerialName("rutube")
    val rutube: Rutube = Rutube()
)