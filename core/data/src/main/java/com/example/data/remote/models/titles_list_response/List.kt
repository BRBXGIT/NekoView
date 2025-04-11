package com.example.data.remote.models.titles_list_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class List(
    @SerialName("1")
    val x1: X1 = X1(),
    @SerialName("2")
    val x2: X2? = X2()
)