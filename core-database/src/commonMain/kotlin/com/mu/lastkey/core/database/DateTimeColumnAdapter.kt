package com.mu.lastkey.core.database

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun getLocalDateTimeColumnAdapter() = object : ColumnAdapter<LocalDateTime, Long> {
    override fun decode(databaseValue: Long): LocalDateTime {
        val instant = Instant.fromEpochMilliseconds(databaseValue)
        return instant.toLocalDateTime(TimeZone.UTC)
    }

    override fun encode(value: LocalDateTime): Long {
        val instant = value.toInstant(TimeZone.UTC)
        return instant.toEpochMilliseconds()
    }
}
