package com.mu.lastkey.feature.password.data.di

import com.mu.lastkey.core.data.mapper.CredentialFieldMapper
import com.mu.lastkey.core.data.mapper.CredentialMapper
import com.mu.lastkey.core.data.mapper.CredentialSectionMapper
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.core.network.realm.RealmClient
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
