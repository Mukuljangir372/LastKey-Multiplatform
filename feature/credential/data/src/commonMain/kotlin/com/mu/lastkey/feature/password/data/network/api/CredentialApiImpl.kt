package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.core.network.realm.model.CredentialRealmModel
import com.mu.lastkey.core.network.realm.model.CredentialSectionFieldRealmModel
import com.mu.lastkey.core.network.realm.model.CredentialSectionRealmModel
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
        credential: CredentialRealmModel
    ) {
        val realm = realmClient.getOpenedRealm()
        realm.writeBlocking {
            copyToRealm(instance = credential, updatePolicy = UpdatePolicy.ALL)
        }
    }

    override suspend fun insertOrReplace(
        section: CredentialSectionRealmModel
    ) {
        val realm = realmClient.getOpenedRealm()
        realm.writeBlocking {
            copyToRealm(instance = section, updatePolicy = UpdatePolicy.ALL)
        }
    }

    override suspend fun insertOrReplace(
        field: CredentialSectionFieldRealmModel
    ) {
        val realm = realmClient.getOpenedRealm()
        realm.writeBlocking {
            copyToRealm(instance = field, updatePolicy = UpdatePolicy.ALL)
        }
    }

    override suspend fun deleteCredentialById(id: String) {
        val realm = realmClient.getOpenedRealm()
        val credential = getCredentialById(id) ?: return
        realm.writeBlocking {
            delete(credential)
        }
    }

    override suspend fun deleteSectionById(id: String) {
        val realm = realmClient.getOpenedRealm()
        val section = getSectionById(id) ?: return
        realm.writeBlocking {
            delete(section)
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
