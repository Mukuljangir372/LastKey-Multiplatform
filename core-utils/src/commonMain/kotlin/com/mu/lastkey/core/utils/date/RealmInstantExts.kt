package com.mu.lastkey.core.utils.date

import io.realm.kotlin.types.RealmInstant
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime

private fun Instant.toRealmInstant(): RealmInstant {
    val sec: Long = this.epochSeconds
    val nano: Int = this.nanosecondsOfSecond
    return if (sec >= 0) {
        RealmInstant.from(sec, nano)
    } else {
        RealmInstant.from(sec + 1, -1_000_000 + nano)
    }
}

private fun RealmInstant.toInstant(): Instant {
    val sec: Long = this.epochSeconds
    val nano: Int = this.nanosecondsOfSecond
    return if (sec >= 0) {
        Instant.fromEpochSeconds(sec, nano.toLong())
    } else {
        Instant.fromEpochSeconds(sec - 1, 1_000_000 + nano.toLong())
    }
}

fun RealmInstant.toLocalDateTime(): LocalDateTime {
    val instant = toInstant()
    return instant.toLocalDateTime()
}

fun LocalDateTime.toRealmInstant(): RealmInstant {
    val localDateTime = this
    return localDateTime.toInstant().toRealmInstant()
}
