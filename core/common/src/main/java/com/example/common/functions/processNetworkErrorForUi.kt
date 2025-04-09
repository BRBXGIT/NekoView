package com.example.common.functions

import com.example.data.remote.utils.NetworkError

fun processNetworkErrorsForUi(error: NetworkError): String {
    return when(error) {
        NetworkError.REQUEST_TIMEOUT -> "ERROR: TIMEOUT"
        NetworkError.UNAUTHORIZED -> "Error: unauthorized"
        NetworkError.CONFLICT -> "Error: conflict"
        NetworkError.TOO_MANY_REQUESTS -> "Give AniLibria a little rest, try in 5 minutes :)"
        NetworkError.NO_INTERNET -> "Error: Internet. Please connect to it :)"
        NetworkError.PAYLOAD_TOO_LARGE -> "Error: payload to large"
        NetworkError.SERVER_ERROR -> "Error: server error, something wrong with manga dex :("
        NetworkError.SERIALIZATION -> "Error: serialization"
        NetworkError.UNKNOWN -> "Error: unknown"
    }
}