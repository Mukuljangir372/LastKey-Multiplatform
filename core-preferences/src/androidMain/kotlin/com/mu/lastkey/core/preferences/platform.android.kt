package com.mu.lastkey.core.preferences

import android.content.Context
import com.mu.lastkey.core.preferences.store.PreferencesStoreConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun getCorePreferencesPlatformModule(): Module {
    return module {
        single { providePreferencesStoreConfig(androidContext()) }
    }
}

private fun providePreferencesStoreConfig(
    context: Context
): PreferencesStoreConfig {
    return PreferencesStoreConfig(storageDirPath = context.filesDir.absolutePath)
}
