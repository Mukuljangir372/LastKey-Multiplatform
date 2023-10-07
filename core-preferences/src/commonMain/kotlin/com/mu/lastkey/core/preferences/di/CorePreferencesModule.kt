package com.mu.lastkey.core.preferences.di

import com.mu.lastkey.core.preferences.store.PreferencesStore
import com.mu.lastkey.core.preferences.store.PreferencesStoreConfig
import com.mu.lastkey.core.preferences.store.PreferencesStoreFactory
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCorePreferencesModule(): Module {
    return module {
        single { providePreferenceStore(get()) }
    }
}

private fun providePreferenceStore(config: PreferencesStoreConfig): PreferencesStore {
    return PreferencesStoreFactory(config).create()
}
