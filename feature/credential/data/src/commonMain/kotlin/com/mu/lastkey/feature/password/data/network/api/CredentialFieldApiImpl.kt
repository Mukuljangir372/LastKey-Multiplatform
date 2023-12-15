package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.core.network.realm.model.CredentialSectionFieldRealmModel
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.query.find
import org.mongodb.kbson.ObjectId

internal class CredentialFieldApiImpl(
    private val realmClient: RealmClient
) : CredentialFieldApi {
    override suspend fun getFields(
        sectionId: String
    ): List<CredentialSectionFieldRealmModel> {
        val realm = realmClient.getOpenedRealm()
        return realm.query(
            clazz = CredentialSectionFieldRealmModel::class,
            query = "sectionId == %0",
            args = arrayOf(sectionId)
        ).find().toList()
    }

    override suspend fun getFieldById(
        id: String
    ): CredentialSectionFieldRealmModel? {
        val realm = realmClient.getOpenedRealm()
        return realm.query(
            clazz = CredentialSectionFieldRealmModel::class,
            query = "_id == %0",
            ObjectId(id)
        ).first().find()
    }

    override suspend fun insertOrReplace(
        field: CredentialSectionFieldRealmModel
    ) {
        val realm = realmClient.getOpenedRealm()
        realm.writeBlocking {
            copyToRealm(instance = field, updatePolicy = UpdatePolicy.ALL)
        }
    }

    override suspend fun deleteFieldById(id: String) {
        val realm = realmClient.getOpenedRealm()
        val field = getFieldById(id) ?: return
        realm.writeBlocking {
            delete(field)
        }
    }
}
