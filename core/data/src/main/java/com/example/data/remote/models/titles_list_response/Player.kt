package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    @SerialName("alternative_player")
    val alternativePlayer: String? = null,
    @SerialName("episodes")
    val episodes: Episodes = Episodes(),
    @SerialName("host")
    val host: String = "",
    @SerialName("is_rutube")
    val isRutube: Boolean = false,
    @SerialName("list")
    val list: List = List(),
    @SerialName("rutube")
    val rutube: Rutube? = Rutube()
)