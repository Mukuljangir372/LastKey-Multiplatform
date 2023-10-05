package com.mu.lastkey.core.utils.json

import kotlinx.serialization.json.Json

class JsonFactory {
    fun create(): Json {
        return Json { ignoreUnknownKeys = true }
    }
}
