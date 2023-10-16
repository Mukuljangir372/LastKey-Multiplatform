package com.mu.lastkey

import com.mu.lastkey.core.data.getCoreDataModule
import com.mu.lastkey.core.logging.di.getCoreLoggingModule
import com.mu.lastkey.core.network.di.getCoreNetworkModule
import com.mu.lastkey.core.preferences.di.getCorePreferencesModule
import com.mu.lastkey.core.preferences.getCorePreferencesPlatformModule
import com.mu.lastkey.core.utils.di.getCoreUtilsModule
import com.mu.lastkey.feature.login.data.di.getLoginDataModule
import org.koin.core.context.startKoin

object DependencyGraph {
    private val dependencies = listOf(
        getCoreLoggingModule(),
        getCoreNetworkModule(),
        getCorePreferencesModule(),
        getCoreUtilsModule(),
        getCorePreferencesPlatformModule(),
        getCoreDataModule(),
        getLoginDataModule()
    )

    fun load() {
        startKoin {
            modules(dependencies)
        }
    }
}