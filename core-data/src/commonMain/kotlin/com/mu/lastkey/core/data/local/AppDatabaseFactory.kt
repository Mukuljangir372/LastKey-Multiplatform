package com.mu.lastkey.core.data.local

import app.cash.sqldelight.db.SqlDriver
import com.mu.lastkey.core.data.AppDatabase

class AppDatabaseFactory(private val driver: SqlDriver) {
    fun create(): AppDatabase {
        return AppDatabase(
            driver = driver
        )
    }
}