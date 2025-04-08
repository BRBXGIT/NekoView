package com.example.data.remote.utils

fun processNetworkErrors(statusCode: Int): Result.Error<NetworkError> {
    return when(statusCode) {
        401 -> Result.Error(NetworkError.UNAUTHORIZED)
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        409 -> Result.Error(NetworkError.CONFLICT)
        413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}