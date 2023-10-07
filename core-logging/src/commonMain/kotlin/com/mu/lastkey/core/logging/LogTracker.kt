package com.mu.lastkey.core.logging

interface LogTracker {
    fun info(message: String, attrs: Map<String, Any>)
    fun debug(message: String)
    fun error(message: String?, throwable: Throwable?)
    fun handledException(throwable: Throwable?)
    fun unExceptedException(throwable: Throwable?, attrs: Map<String, Any>)
}
