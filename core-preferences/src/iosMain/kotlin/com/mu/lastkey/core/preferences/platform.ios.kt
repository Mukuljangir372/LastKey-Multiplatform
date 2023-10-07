package com.mu.lastkey.core.preferences

import com.mu.lastkey.core.preferences.store.PreferencesStoreConfig
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun getCorePreferencesPlatformModule(): Module {
    return module {
        single { providePreferencesStoreConfig() }
    }
}

private fun providePreferencesStoreConfig(): PreferencesStoreConfig {
    val dir = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    return PreferencesStoreConfig(storageDirPath = dir?.path!!)
}
