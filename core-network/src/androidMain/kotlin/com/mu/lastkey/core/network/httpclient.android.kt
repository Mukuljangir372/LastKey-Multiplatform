package com.mu.lastkey.core.network

import com.mu.lastkey.core.logging.LogTracker
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class HttpClientFactory {
    actual fun create(logger: LogTracker): HttpClient {
        return HttpClient(OkHttp) {
            install(Logging) {
                level = LogLevel.ALL
                this.logger = object : Logger {
                    override fun log(message: String) {
                        logger.info(message, emptyMap())
                    }
                }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}
