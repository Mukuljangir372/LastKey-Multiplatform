package com.mu.lastkey.core.utils.model

import com.mu.lastkey.core.utils.generateUUID

data class MessageWrapper(val id: String, val message: String) {
    companion object {
        val empty = MessageWrapper(id = "", message = "")
        fun create(message: String): MessageWrapper {
            return MessageWrapper(id = generateUUID(), message = message)
        }
    }
}
