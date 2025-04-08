package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Posters(
    @SerialName("medium")
    val medium: Medium = Medium(),
    @SerialName("original")
    val original: Original = Original(),
    @SerialName("small")
    val small: Small = Small()
)