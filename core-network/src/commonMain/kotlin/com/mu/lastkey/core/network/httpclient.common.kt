package com.mu.lastkey.core.network

import com.mu.lastkey.core.logging.api.LogTracker
import io.ktor.client.HttpClient

expect class HttpClientFactory constructor() {
    fun create(logger: LogTracker): HttpClient
}
