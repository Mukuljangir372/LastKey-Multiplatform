package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.core.network.realm.model.CredentialRealmModel
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.query.find
import org.mongodb.kbson.ObjectId

internal class CredentialApiImpl(
    private val realmClient: RealmClient
) : CredentialApi {
    override suspend fun getCredentialsByOffset(
        offset: Int,
        limit: Int
    ): List<CredentialRealmModel> {
        val realm = realmClient.getOpenedRealm()
        return realm.query(CredentialRealmModel::class)
            .find()
            .subList(offset, offset + limit)
    }

    override suspend fun getCredentialById(
        id: String
    ): CredentialRealmModel? {
        val realm = realmClient.getOpenedRealm()
        return realm.query(
            clazz = CredentialRealmModel::class,
            query = "_id == %0",
            ObjectId(id)
        ).first().find()
    }

    override suspend fun insertOrReplace(
        credential: CredentialRealmModel
    ) {
        val realm = realmClient.getOpenedRealm()
        realm.writeBlocking {
            copyToRealm(instance = credential, updatePolicy = UpdatePolicy.ALL)
        }
    }

    override suspend fun deleteCredentialById(id: String) {
        val realm = realmClient.getOpenedRealm()
        val credential = getCredentialById(id) ?: return
        realm.writeBlocking {
            delete(credential)
        }
    }
}
