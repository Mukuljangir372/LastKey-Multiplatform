package com.mu.lastkey.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class AppDatabaseDriverFactory(private val context: Context) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            context = context,
            name = "app.db",
            schema = AppDatabase.Schema
        )
    }
}