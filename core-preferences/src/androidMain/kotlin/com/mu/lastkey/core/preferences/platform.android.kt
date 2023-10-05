package com.mu.lastkey.core.preferences

import android.content.Context
import com.mu.lastkey.core.preferences.store.PreferencesStoreConfig
import com.mu.lastkey.core.utils.di.AndroidContext
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

actual fun getPreferencesPlatformModule(): DI.Module {
    return DI.Module(name = "preferencesPlatformModule") {
        bindSingleton { providePreferencesStoreConfig(instance(tag = AndroidContext)) }
    }
}

private fun providePreferencesStoreConfig(context: Context): PreferencesStoreConfig {
    return PreferencesStoreConfig(storageDirPath = context.filesDir.absolutePath)
}
