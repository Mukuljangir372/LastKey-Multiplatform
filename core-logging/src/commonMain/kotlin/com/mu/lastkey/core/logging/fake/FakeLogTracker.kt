package com.mu.lastkey.core.logging.fake

import com.mu.lastkey.core.logging.api.LogTracker

class FakeLogTracker : LogTracker {
    override fun info(message: String, attrs: Map<String, Any>) {
    }

    override fun debug(message: String) {
    }

    override fun error(message: String?, throwable: Throwable?) {
    }

    override fun handledException(throwable: Throwable?) {
    }

    override fun unExceptedException(throwable: Throwable?, attrs: Map<String, Any>) {
    }
}
