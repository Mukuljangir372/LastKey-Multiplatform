package com.mu.lastkey.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class AppDatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            name = "app.db",
            schema = AppDatabase.Schema
        )
    }
}