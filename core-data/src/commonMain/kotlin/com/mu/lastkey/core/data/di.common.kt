package com.mu.lastkey.core.data

import app.cash.sqldelight.db.SqlDriver
import com.mu.lastkey.core.data.local.AppDatabaseFactory
import com.mu.lastkey.core.domain.model.coroutine.AppCoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val APP_DATABASE_DRIVER = "APP_DATABASE_DRIVER"

expect fun getCoreDataPlatformModule(): Module

fun getCoreDataModule(): Module {
    return module {
        single { provideAppCoroutineDispatchers() }
        single { provideAppDatabase(get(named(APP_DATABASE_DRIVER))) }
    }
}

private fun provideAppCoroutineDispatchers(): AppCoroutineDispatchers {
    return AppCoroutineDispatchers(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        default = Dispatchers.Default
    )
}

private fun provideAppDatabase(driver: SqlDriver): AppDatabase {
    return AppDatabaseFactory(driver).create()
}