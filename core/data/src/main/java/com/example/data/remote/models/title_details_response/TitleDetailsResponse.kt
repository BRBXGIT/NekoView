package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List

@Serializable
data class TitleDetailsResponse(
    @SerialName("announce")
    val announce: String? = "",
    @SerialName("blocked")
    val blocked: Blocked = Blocked(),
    @SerialName("code")
    val code: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("franchises")
    val franchises: List<Franchise> = listOf(),
    @SerialName("genres")
    val genres: List<String> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("in_favorites")
    val inFavorites: Int = 0,
    @SerialName("last_change")
    val lastChange: Int = 0,
    @SerialName("names")
    val names: NamesX = NamesX(),
    @SerialName("player")
    val player: Player = Player(),
    @SerialName("posters")
    val posters: Posters = Posters(),
    @SerialName("season")
    val season: Season = Season(),
    @SerialName("status")
    val status: Status = Status(),
    @SerialName("team")
    val team: Team = Team(),
    @SerialName("torrents")
    val torrents: Torrents = Torrents(),
    @SerialName("type")
    val type: Type = Type(),
    @SerialName("updated")
    val updated: Int = 0
)