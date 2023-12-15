package com.mu.lastkey.core.data

import app.cash.sqldelight.db.SqlDriver

expect class AppDatabaseDriverFactory {
    fun create(): SqlDriver
}
