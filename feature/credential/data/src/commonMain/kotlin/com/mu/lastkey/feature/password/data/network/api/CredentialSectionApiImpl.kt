package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.core.network.realm.model.CredentialSectionRealmModel
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.query.find
import org.mongodb.kbson.ObjectId

internal class CredentialSectionApiImpl(
    private val realmClient: RealmClient
) : CredentialSectionApi {
    override suspend fun getSections(
        credentialId: String
    ): List<CredentialSectionRealmModel> {
        val realm = realmClient.getOpenedRealm()
        return realm.query(
            clazz = CredentialSectionRealmModel::class,
            query = "credentialId == %0",
            args = arrayOf(credentialId)
        ).find().toList()
    }

    override suspend fun getSectionById(
        id: String
    ): CredentialSectionRealmModel? {
        val realm = realmClient.getOpenedRealm()
        return realm.query(
            clazz = CredentialSectionRealmModel::class,
            query = "_id == %0",
            ObjectId(id)
        ).first().find()
    }

    override suspend fun insertOrReplace(
        section: CredentialSectionRealmModel
    ) {
        val realm = realmClient.getOpenedRealm()
        realm.writeBlocking {
            copyToRealm(instance = section, updatePolicy = UpdatePolicy.ALL)
        }
    }

    override suspend fun deleteSectionById(id: String) {
        val realm = realmClient.getOpenedRealm()
        val section = getSectionById(id) ?: return
        realm.writeBlocking {
            delete(section)
        }
    }
}
