package com.example.data.remote.models.user_session_token_response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSessionTokenResponse(
    @SerialName("err")
    val err: String = "",
    @SerialName("key")
    val key: String = "",
    @SerialName("mes")
    val mes: String = "",
    @SerialName("sessionId")
    val sessionId: String = ""
)