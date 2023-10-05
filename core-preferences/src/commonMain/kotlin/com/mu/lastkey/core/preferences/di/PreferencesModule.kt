package com.mu.lastkey.core.preferences.di

import com.mu.lastkey.core.preferences.store.PreferencesStore
import com.mu.lastkey.core.preferences.store.PreferencesStoreConfig
import com.mu.lastkey.core.preferences.store.PreferencesStoreFactory
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

fun getPreferencesModule(): DI.Module {
    return DI.Module(name = "preferencesModule") {
        bindSingleton { providePreferenceStore(instance()) }
    }
}

private fun providePreferenceStore(config: PreferencesStoreConfig): PreferencesStore {
    return PreferencesStoreFactory(config).create()
}
