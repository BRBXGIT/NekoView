package com.example.data.remote.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(): HttpClient {
    val engine = OkHttp.create()
    val httpClient = HttpClient(engine) {
        install(Logging)
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    allowSpecialFloatingPointValues = true
                    coerceInputValues = true
                    explicitNulls = false
                }
            )
        }
        install(HttpTimeout) {
            this.requestTimeoutMillis = 60000 //One minute
            this.connectTimeoutMillis = 60000 //Minute again
            this.socketTimeoutMillis = 60000 //And minute again :)
        }
    }

    return httpClient
}