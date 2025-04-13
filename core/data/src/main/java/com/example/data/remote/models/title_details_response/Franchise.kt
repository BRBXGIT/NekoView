package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List

@Serializable
data class Franchise(
    @SerialName("franchise")
    val franchise: FranchiseX = FranchiseX(),
    @SerialName("releases")
    val releases: List<Release> = listOf()
)