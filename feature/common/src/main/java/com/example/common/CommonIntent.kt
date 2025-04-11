package com.example.common

sealed class CommonIntent {
    data class SetNavIndex(val index: Int): CommonIntent()
    data class GetUserToken(
        val email: String,
        val password: String
    ): CommonIntent()
}