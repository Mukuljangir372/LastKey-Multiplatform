package com.mu.lastkey.core.preferences

import com.mu.lastkey.core.preferences.store.PreferencesStoreConfig
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun getPreferencesPlatformModule(): DI.Module {
    return DI.Module(name = "preferencesPlatformModule") {
        bindSingleton { providePreferencesStoreConfig() }
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
