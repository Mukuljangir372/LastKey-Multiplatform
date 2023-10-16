package com.mu.lastkey.core.data

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun getCoreDataPlatformModule(): Module {
    return module {
        single(named(APP_DATABASE_DRIVER)) { provideAppDatabaseDriver() }
    }
}

private fun provideAppDatabaseDriver(): SqlDriver {
    return AppDatabaseDriverFactory().create()
}