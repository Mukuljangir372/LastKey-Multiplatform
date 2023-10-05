package com.mu.lastkey.core.database

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T : Any> getJsonColumnAdapter(json: Json): ColumnAdapter<T, String> {
    return object : ColumnAdapter<T, String> {
        override fun decode(databaseValue: String): T {
            return json.decodeFromString(databaseValue)
        }
        override fun encode(value: T): String {
            return json.encodeToString(value)
        }
    }
}
