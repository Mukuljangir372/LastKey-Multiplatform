package com.mu.lastkey.core.preferences.store

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import okio.Path.Companion.toPath

internal class PreferencesStoreFactory(private val config: PreferencesStoreConfig) {
    companion object {
        private const val STORE_FILE = "last.key.pref.store"
    }

    fun create(): PreferencesStore {
        val datastore = PreferenceDataStoreFactory.createWithPath {
            (config.storageDirPath + "/$STORE_FILE").toPath()
        }
        return PreferencesStoreImpl(store = datastore)
    }
}
