package com.mu.lastkey.core.data

import app.cash.sqldelight.db.SqlDriver
import com.mu.lastkey.core.data.local.AppDatabaseFactory
import com.mu.lastkey.core.data.local.AuthUserLocalDataSource
import com.mu.lastkey.core.data.local.AuthUserLocalDataSourceImpl
import com.mu.lastkey.core.data.local.CredentialFieldLocalDataSource
import com.mu.lastkey.core.data.local.CredentialFieldLocalDataSourceImpl
import com.mu.lastkey.core.data.local.CredentialLocalDataSource
import com.mu.lastkey.core.data.local.CredentialLocalDataSourceImpl
import com.mu.lastkey.core.data.local.CredentialSectionLocalDataSource
import com.mu.lastkey.core.data.local.CredentialSectionLocalDataSourceImpl
import com.mu.lastkey.core.data.mapper.AuthUserMapper
import com.mu.lastkey.core.data.mapper.AuthUserMapperImpl
import com.mu.lastkey.core.data.mapper.CredentialFieldMapper
import com.mu.lastkey.core.data.mapper.CredentialFieldMapperImpl
import com.mu.lastkey.core.data.mapper.CredentialMapper
import com.mu.lastkey.core.data.mapper.CredentialMapperImpl
import com.mu.lastkey.core.data.mapper.CredentialSectionMapper
import com.mu.lastkey.core.data.mapper.CredentialSectionMapperImpl
import com.mu.lastkey.core.data.repository.AuthUserRepositoryImpl
import com.mu.lastkey.core.data.usecase.GetActiveAuthSessionUsecaseImpl
import com.mu.lastkey.core.domain.InputValidator
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.core.domain.model.AppStrings
import com.mu.lastkey.core.domain.repository.AuthUserRepository
import com.mu.lastkey.core.domain.usecase.GetActiveAuthSessionUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val APP_DATABASE_DRIVER = "APP_DATABASE_DRIVER"

expect fun getCoreDataPlatformModule(): Module

fun getCoreDataModule(): Module {
    return module {
        single { provideAppStrings() }
        single { provideInputValidator() }
        single { provideAppCoroutineDispatchers() }
        single { provideAppDatabase(get(named(APP_DATABASE_DRIVER)), get()) }
        single { provideAuthUserMapper() }
        single { provideCredentialMapper() }
        single { provideCredentialSectionMapper(get()) }
        single { provideCredentialFieldMapper() }
        single { provideAuthUserLocalDataSource(get(), get()) }
        single { provideCredentialLocalDataSource(get(), get()) }
        single { provideCredentialSectionLocalDataSource(get()) }
        single { provideCredentialFieldLocalDataSource(get()) }
        single { provideAuthUserRepository(get(), get(), get()) }
        single { provideGetActiveAuthSessionUsecase(get()) }
    }
}

private fun provideAppStrings(): AppStrings {
    return AppStrings.en
}

private fun provideAppCoroutineDispatchers(): AppCoroutineDispatchers {
    return AppCoroutineDispatchers(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        default = Dispatchers.Default
    )
}

private fun provideAppDatabase(driver: SqlDriver, json: Json): AppDatabase {
    return AppDatabaseFactory(driver, json).create()
}

private fun provideAuthUserMapper(): AuthUserMapper {
    return AuthUserMapperImpl()
}

private fun provideCredentialMapper(): CredentialMapper {
    return CredentialMapperImpl()
}

private fun provideCredentialSectionMapper(
    fieldMapper: CredentialFieldMapper
): CredentialSectionMapper {
    return CredentialSectionMapperImpl(fieldMapper)
}

private fun provideCredentialFieldMapper(): CredentialFieldMapper {
    return CredentialFieldMapperImpl()
}

private fun provideInputValidator(): InputValidator {
    return InputValidatorImpl()
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

private fun provideCredentialLocalDataSource(
    appDatabase: AppDatabase,
    credentialMapper: CredentialMapper
): CredentialLocalDataSource {
    return CredentialLocalDataSourceImpl(
        database = appDatabase,
        mapper = credentialMapper
    )
}

private fun provideCredentialSectionLocalDataSource(
    appDatabase: AppDatabase
): CredentialSectionLocalDataSource {
    return CredentialSectionLocalDataSourceImpl(
        database = appDatabase
    )
}

private fun provideCredentialFieldLocalDataSource(
    appDatabase: AppDatabase
): CredentialFieldLocalDataSource {
    return CredentialFieldLocalDataSourceImpl(
        database = appDatabase
    )
}

private fun provideAuthUserRepository(
    authUserLocalDataSource: AuthUserLocalDataSource,
    authUserMapper: AuthUserMapper,
    coroutineDispatchers: AppCoroutineDispatchers
): AuthUserRepository {
    return AuthUserRepositoryImpl(
        localDataSource = authUserLocalDataSource,
        mapper = authUserMapper,
        coroutineDispatcher = coroutineDispatchers.default
    )
}

private fun provideGetActiveAuthSessionUsecase(
    authUserRepository: AuthUserRepository
): GetActiveAuthSessionUsecase {
    return GetActiveAuthSessionUsecaseImpl(
        authUserRepository = authUserRepository
    )
}
