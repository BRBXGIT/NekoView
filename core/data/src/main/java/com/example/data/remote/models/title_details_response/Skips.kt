package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List

@Serializable
data class Skips(
    @SerialName("ending")
    val ending: List<Int?> = listOf(),
    @SerialName("opening")
    val opening: List<Int?> = listOf()
)