package com.mu.lastkey.core.utils.uuid

import com.mu.lastkey.core.utils.generateUUID

internal class UUIDGeneratorImpl : UUIDGenerator {
    override fun generate(): String {
        return generateUUID()
    }
}
