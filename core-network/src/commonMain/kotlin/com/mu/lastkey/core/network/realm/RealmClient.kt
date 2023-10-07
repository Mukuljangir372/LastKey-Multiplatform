package com.mu.lastkey.core.network.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App

interface RealmClient {
    suspend fun getApp(): App
    suspend fun getOpenedRealm(): Realm
}
