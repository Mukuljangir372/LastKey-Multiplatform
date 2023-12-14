package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.model.CredentialSectionRealmModel

internal interface CredentialSectionApi {
    suspend fun getSections(credentialId: String): List<CredentialSectionRealmModel>
    suspend fun getSectionById(id: String): CredentialSectionRealmModel?
    suspend fun insertOrReplace(section: CredentialSectionRealmModel)
    suspend fun deleteSectionById(id: String)
}
