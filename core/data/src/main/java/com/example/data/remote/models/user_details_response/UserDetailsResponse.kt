package com.example.data.remote.models.user_details_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsResponse(
    @SerialName("avatar_original")
    val avatarOriginal: String = "",
    @SerialName("avatar_thumbnail")
    val avatarThumbnail: String = "",
    @SerialName("email")
    val email: String = "",
    @SerialName("login")
    val login: String = "",
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("patreon_id")
    val patreonId: String = "",
    @SerialName("vk_id")
    val vkId: String = ""
)