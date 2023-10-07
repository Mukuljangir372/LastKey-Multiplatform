package com.mu.lastkey.core.logging

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class DebugLogTracker : LogTracker {
    init {
        Napier.base(DebugAntilog())
    }

    override fun info(message: String, attrs: Map<String, Any>) {
        Napier.i("$message attrs:$attrs")
    }

    override fun debug(message: String) {
        Napier.d(message)
    }

    override fun error(message: String?, throwable: Throwable?) {
        Napier.e(message ?: throwable?.message ?: "", throwable)
    }

    override fun handledException(throwable: Throwable?) {
        Napier.e(throwable?.message ?: "", throwable)
    }

    override fun unExceptedException(throwable: Throwable?, attrs: Map<String, Any>) {
        Napier.e("${throwable?.message.orEmpty()} attrs: $attrs", throwable)
    }
}
