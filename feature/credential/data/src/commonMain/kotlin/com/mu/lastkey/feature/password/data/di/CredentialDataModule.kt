package com.mu.lastkey.feature.password.data.di

import com.mu.lastkey.core.data.mapper.CredentialMapper
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.feature.password.data.network.CredentialNetworkDataSource
import com.mu.lastkey.feature.password.data.network.CredentialNetworkDataSourceImpl
import com.mu.lastkey.feature.password.data.network.api.CredentialApi
import com.mu.lastkey.feature.password.data.network.api.CredentialApiImpl
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
            provideCredentialNetworkDataSource(
                credentialApi = get(),
                credentialMapper = get(),
                dispatcher = get<AppCoroutineDispatchers>().default
            )
        }
    }
}

private fun provideCredentialApi(realmClient: RealmClient): CredentialApi {
    return CredentialApiImpl(realmClient)
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
