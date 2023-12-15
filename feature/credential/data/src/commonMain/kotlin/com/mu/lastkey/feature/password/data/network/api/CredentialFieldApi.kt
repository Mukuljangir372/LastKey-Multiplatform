package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.model.CredentialSectionFieldRealmModel

internal interface CredentialFieldApi {
    suspend fun getFields(sectionId: String): List<CredentialSectionFieldRealmModel>
    suspend fun getFieldById(id: String): CredentialSectionFieldRealmModel?
    suspend fun insertOrReplace(field: CredentialSectionFieldRealmModel)
    suspend fun deleteFieldById(id: String)
}
