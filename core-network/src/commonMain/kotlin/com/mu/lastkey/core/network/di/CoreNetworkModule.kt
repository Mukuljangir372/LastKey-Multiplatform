package com.mu.lastkey.core.network.di

import com.mu.lastkey.core.logging.LogTracker
import com.mu.lastkey.core.logging.di.DEBUG_LOG_TRACKER
import com.mu.lastkey.core.network.HttpClientFactory
import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.core.network.realm.RealmClientImpl
import com.mu.lastkey.core.network.realm.RealmConfig
import com.mu.lastkey.core.network.realm.RealmConfigFactory
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun getCoreNetworkModule(): Module {
    return module {
        single { provideHttpClient(get(named(DEBUG_LOG_TRACKER))) }
        single { provideRealmClient(get()) }
        single { provideRealmConfig() }
    }
}

private fun provideHttpClient(logTracker: LogTracker): HttpClient {
    return HttpClientFactory().create(logTracker)
}

private fun provideRealmClient(config: RealmConfig): RealmClient {
    return RealmClientImpl(config)
}

private fun provideRealmConfig(): RealmConfig {
    return RealmConfigFactory().create()
}
