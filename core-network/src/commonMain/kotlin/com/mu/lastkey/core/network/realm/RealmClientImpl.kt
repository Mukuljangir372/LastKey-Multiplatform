package com.mu.lastkey.core.network.realm

import com.mu.lastkey.core.network.realm.exception.UserNotFoundException
import com.mu.lastkey.core.network.realm.model.CredentialRealmModel
import com.mu.lastkey.core.network.realm.model.CredentialSectionFieldRealmModel
import com.mu.lastkey.core.network.realm.model.CredentialSectionRealmModel
import com.mu.lastkey.core.network.realm.model.UserRealmModel
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.types.BaseRealmObject
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.reflect.KClass

internal class RealmClientImpl(
    private val config: RealmConfig
) : RealmClient {
    private var loadedApp: App? = null
    private var loadedRealm: Realm? = null
    private var mutex = Mutex()

    override suspend fun getApp(): App {
        if (loadedApp != null) return loadedApp!!
        mutex.tryLock()
        val appConfiguration = AppConfiguration.Builder(config.appId)
        val createdApp = App.create(appConfiguration.build())
        loadedApp = createdApp
        mutex.unlock()
        return loadedApp!!
    }

    override suspend fun getOpenedRealm(): Realm {
        val user = getApp().currentUser ?: throw UserNotFoundException()
        if (loadedRealm != null) return loadedRealm!!
        mutex.tryLock()
        val configuration = SyncConfiguration.Builder(user, config.appId, getSchemas())
        val realm = Realm.open(configuration.build())
        loadedRealm = realm
        mutex.unlock()
        return loadedRealm!!
    }

    companion object {
        private fun getSchemas(): Set<KClass<out BaseRealmObject>> {
            return setOf(
                UserRealmModel::class,
                CredentialSectionFieldRealmModel::class,
                CredentialSectionRealmModel::class,
                CredentialRealmModel::class
            )
        }
    }
}
