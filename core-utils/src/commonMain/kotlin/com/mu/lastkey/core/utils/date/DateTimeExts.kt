package com.mu.lastkey.core.utils.date

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun currentLocalDateTime(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.UTC)
}

fun Instant.toLocalDateTime(): LocalDateTime {
    val instant = this
    return instant.toLocalDateTime(TimeZone.UTC)
}

fun LocalDateTime.toInstant(): Instant {
    val localDateTime = this
    return localDateTime.toInstant(TimeZone.UTC)
}
