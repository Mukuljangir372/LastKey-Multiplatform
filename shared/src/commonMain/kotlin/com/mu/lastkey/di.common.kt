package com.mu.lastkey

import com.mu.lastkey.core.data.getCoreDataModule
import com.mu.lastkey.core.data.getCoreDataPlatformModule
import com.mu.lastkey.core.logging.di.getCoreLoggingModule
import com.mu.lastkey.core.network.di.getCoreNetworkModule
import com.mu.lastkey.core.preferences.di.getCorePreferencesModule
import com.mu.lastkey.core.preferences.getCorePreferencesPlatformModule
import com.mu.lastkey.core.utils.di.getCoreUtilsModule
import com.mu.lastkey.feature.login.data.di.getLoginDataModule
import com.mu.lastkey.feature.login.ui.di.getLoginUiModule

internal val dependencies = listOf(
    getCoreLoggingModule(),
    getCoreNetworkModule(),
    getCorePreferencesModule(),
    getCoreUtilsModule(),
    getCorePreferencesPlatformModule(),
    getCoreDataPlatformModule(),
    getCoreDataModule(),
    getLoginDataModule(),
    getLoginUiModule()
)

expect class DependencyGraph {
    fun load()
}
