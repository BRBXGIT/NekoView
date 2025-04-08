package com.example.data.remote.models.titles_updates_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("current_page")
    val currentPage: Int = 0,
    @SerialName("items_per_page")
    val itemsPerPage: Int = 0,
    @SerialName("pages")
    val pages: Int = 0,
    @SerialName("total_items")
    val totalItems: Int = 0
)