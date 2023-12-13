package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.model.CredentialRealmModel
import com.mu.lastkey.core.network.realm.model.CredentialSectionFieldRealmModel
import com.mu.lastkey.core.network.realm.model.CredentialSectionRealmModel

internal interface CredentialApi {
    suspend fun getCredentialsByOffset(offset: Int, limit: Int): List<CredentialRealmModel>
    suspend fun getSections(credentialId: String): List<CredentialSectionRealmModel>
    suspend fun getFields(sectionId: String): List<CredentialSectionFieldRealmModel>
    suspend fun getCredentialById(id: String): CredentialRealmModel?
    suspend fun getSectionById(id: String): CredentialSectionRealmModel?
    suspend fun getFieldById(id: String): CredentialSectionFieldRealmModel?
    suspend fun insertOrReplace(credential: CredentialRealmModel)
    suspend fun insertOrReplace(section: CredentialSectionRealmModel)
    suspend fun insertOrReplace(field: CredentialSectionFieldRealmModel)
    suspend fun deleteCredentialById(id: String)
    suspend fun deleteSectionById(id: String)
    suspend fun deleteFieldById(id: String)
}
