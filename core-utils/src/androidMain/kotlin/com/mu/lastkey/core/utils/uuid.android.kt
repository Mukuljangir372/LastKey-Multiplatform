package com.mu.lastkey.core.utils

import java.util.UUID

actual fun generateUUID(): String {
    return UUID.randomUUID().toString()
}

actual fun generateLongUUID(): Long {
    return UUID.randomUUID().mostSignificantBits
}
