package com.mu.lastkey.core.data

import app.cash.sqldelight.db.SqlDriver
import com.mu.lastkey.core.data.local.AppDatabaseFactory
import com.mu.lastkey.core.data.local.AuthUserLocalDataSource
import com.mu.lastkey.core.data.local.AuthUserLocalDataSourceImpl
import com.mu.lastkey.core.data.mapper.AuthUserMapper
import com.mu.lastkey.core.data.mapper.AuthUserMapperImpl
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
        single { provideAuthUserMapper() }
        single { provideAuthUserLocalDataSource(get(), get()) }
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

private fun provideAuthUserMapper(): AuthUserMapper {
    return AuthUserMapperImpl()
}

private fun provideAuthUserLocalDataSource(
    appDatabase: AppDatabase,
    authUserMapper: AuthUserMapper
): AuthUserLocalDataSource {
    return AuthUserLocalDataSourceImpl(
        appDatabase = appDatabase,
        authUserMapper = authUserMapper
    )
}
