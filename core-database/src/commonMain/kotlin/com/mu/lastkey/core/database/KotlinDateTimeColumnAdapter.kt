package com.mu.lastkey.core.database

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KotlinDateTimeColumnAdapter(private val json: Json) : ColumnAdapter<LocalDateTime, String> {
    override fun decode(databaseValue: String): LocalDateTime {
        return json.decodeFromString(databaseValue)
    }
    override fun encode(value: LocalDateTime): String {
        return json.encodeToString(value)
    }
}
