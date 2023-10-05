package com.mu.lastkey.core.preferences.store

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferencesStore {
    suspend fun <T> read(key: Preferences.Key<T>): T?
    suspend fun <T> observe(key: Preferences.Key<T>): Flow<T?>
    suspend fun <T> write(key: Preferences.Key<T>, value: T): T?
}
