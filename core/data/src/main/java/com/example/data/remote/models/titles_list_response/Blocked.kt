package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List

@Serializable
data class Blocked(
    @SerialName("copyrights")
    val copyrights: Boolean = false,
    @SerialName("geoip")
    val geoip: Boolean = false,
    @SerialName("geoip_list")
    val geoipList: List<String?> = listOf()
)