package com.mu.lastkey.core.utils

import platform.Foundation.NSUUID

actual fun generateUUID(): String {
    return NSUUID().UUIDString
}

actual fun generateLongUUID(): Long {
    return NSUUID().hash.toLong()
}
