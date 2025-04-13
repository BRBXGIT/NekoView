package com.example.data.remote.models.title_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class List(
    @SerialName("1")
    val x1: X1 = X1()
)