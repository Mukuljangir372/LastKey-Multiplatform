package com.mu.lastkey.core.preferences.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class PreferencesStoreImpl(private val store: DataStore<Preferences>) : PreferencesStore {
    override suspend fun <T> read(key: Preferences.Key<T>): T? {
        return observe(key).first()
    }

    override suspend fun <T> observe(key: Preferences.Key<T>): Flow<T?> {
        return store.data.map { it[key] }
    }

    override suspend fun <T> write(key: Preferences.Key<T>, value: T): T? {
        store.edit { it[key] = value }
        return value
    }
}
