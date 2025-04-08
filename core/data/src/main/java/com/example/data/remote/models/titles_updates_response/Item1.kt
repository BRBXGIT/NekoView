package com.example.data.remote.models.titles_updates_response

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item1(
    @SerialName("announce")
    val announce: String? = null,
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