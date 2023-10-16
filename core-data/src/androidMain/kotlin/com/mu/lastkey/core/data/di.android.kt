package com.mu.lastkey.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun getCoreDataPlatformModule(): Module {
    return module {
        single(named(APP_DATABASE_DRIVER)) { provideAppDatabaseDriver(androidContext()) }
    }
}

private fun provideAppDatabaseDriver(context: Context): SqlDriver {
    return AppDatabaseDriverFactory(context).create()
}