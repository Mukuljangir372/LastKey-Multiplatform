package com.mu.lastkey.feature.password.data.di

import com.mu.lastkey.core.data.local.CredentialLocalDataSource
import com.mu.lastkey.core.data.mapper.CredentialFieldMapper
import com.mu.lastkey.core.data.mapper.CredentialMapper
import com.mu.lastkey.core.data.mapper.CredentialSectionMapper
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.core.utils.uuid.UUIDGenerator
import com.mu.lastkey.feature.password.data.network.CredentialFieldNetworkDataSource
import com.mu.lastkey.feature.password.data.network.CredentialFieldNetworkDataSourceImpl
import com.mu.lastkey.feature.password.data.network.CredentialNetworkDataSource
import com.mu.lastkey.feature.password.data.network.CredentialNetworkDataSourceImpl
import com.mu.lastkey.feature.password.data.network.CredentialSectionNetworkDataSource
import com.mu.lastkey.feature.password.data.network.CredentialSectionNetworkDataSourceImpl
import com.mu.lastkey.feature.password.data.network.api.CredentialApi
import com.mu.lastkey.feature.password.data.network.api.CredentialApiImpl
import com.mu.lastkey.feature.password.data.network.api.CredentialFieldApi
import com.mu.lastkey.feature.password.data.network.api.CredentialFieldApiImpl
import com.mu.lastkey.feature.password.data.network.api.CredentialSectionApi
import com.mu.lastkey.feature.password.data.network.api.CredentialSectionApiImpl
import com.mu.lastkey.feature.password.data.repository.CredentialRepositoryImpl
import com.mu.lastkey.feature.password.data.repository.store.CredentialsDataStore
import com.mu.lastkey.feature.password.data.repository.store.CredentialsDataStoreImpl
import com.mu.lastkey.feature.password.data.repository.store.CredentialsStoreProvider
import com.mu.lastkey.feature.password.domain.repository.CredentialRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCredentialDataModule(): Module {
    return module {
        single {
            provideCredentialApi(
                realmClient = get()
            )
        }
        single {
            provideCredentialSectionApi(
                realmClient = get()
            )
        }
        single {
            provideCredentialFieldApi(
                realmClient = get()
            )
        }
        single {
            provideCredentialNetworkDataSource(
                credentialApi = get(),
                credentialMapper = get(),
                dispatcher = get<AppCoroutineDispatchers>().default
            )
        }
        single {
            provideCredentialSectionNetworkDataSource(
                sectionApi = get(),
                sectionMapper = get(),
                dispatcher = get<AppCoroutineDispatchers>().default
            )
        }
        single {
            provideCredentialFieldNetworkDataSource(
                fieldApi = get(),
                fieldMapper = get(),
                dispatcher = get<AppCoroutineDispatchers>().default
            )
        }
        single {
            provideCredentialsStoreProvider(
                networkDataSource = get(),
                localDataSource = get(),
                mapper = get(),
                dispatcher = get<AppCoroutineDispatchers>().default,
                uuidGenerator = get()
            )
        }
        single {
            provideCredentialsDataStore(
                storeProvider = get()
            )
        }
        single {
            provideCredentialRepository(
                credentialsDataStore = get()
            )
        }
    }
}

private fun provideCredentialApi(
    realmClient: RealmClient
): CredentialApi {
    return CredentialApiImpl(realmClient)
}

private fun provideCredentialSectionApi(
    realmClient: RealmClient
): CredentialSectionApi {
    return CredentialSectionApiImpl(realmClient)
}

private fun provideCredentialFieldApi(
    realmClient: RealmClient
): CredentialFieldApi {
    return CredentialFieldApiImpl(realmClient)
}

private fun provideCredentialNetworkDataSource(
    credentialApi: CredentialApi,
    credentialMapper: CredentialMapper,
    dispatcher: CoroutineDispatcher
): CredentialNetworkDataSource {
    return CredentialNetworkDataSourceImpl(
        credentialApi = credentialApi,
        credentialMapper = credentialMapper,
        dispatcher = dispatcher
    )
}

private fun provideCredentialSectionNetworkDataSource(
    sectionApi: CredentialSectionApi,
    sectionMapper: CredentialSectionMapper,
    dispatcher: CoroutineDispatcher
): CredentialSectionNetworkDataSource {
    return CredentialSectionNetworkDataSourceImpl(
        sectionApi = sectionApi,
        mapper = sectionMapper,
        dispatcher = dispatcher
    )
}

private fun provideCredentialFieldNetworkDataSource(
    fieldApi: CredentialFieldApi,
    fieldMapper: CredentialFieldMapper,
    dispatcher: CoroutineDispatcher
): CredentialFieldNetworkDataSource {
    return CredentialFieldNetworkDataSourceImpl(
        fieldApi = fieldApi,
        mapper = fieldMapper,
        dispatcher = dispatcher
    )
}

private fun provideCredentialsStoreProvider(
    networkDataSource: CredentialNetworkDataSource,
    localDataSource: CredentialLocalDataSource,
    dispatcher: CoroutineDispatcher,
    mapper: CredentialMapper,
    uuidGenerator: UUIDGenerator
): CredentialsStoreProvider {
    return CredentialsStoreProvider(
        networkDataSource = networkDataSource,
        localDataSource = localDataSource,
        dispatcher = dispatcher,
        mapper = mapper,
        uuidGenerator = uuidGenerator
    )
}

private fun provideCredentialsDataStore(
    storeProvider: CredentialsStoreProvider
): CredentialsDataStore {
    return CredentialsDataStoreImpl(
        storeProvider = storeProvider
    )
}

private fun provideCredentialRepository(
    credentialsDataStore: CredentialsDataStore
): CredentialRepository {
    return CredentialRepositoryImpl(
        credentialsDataStore = credentialsDataStore
    )
}
