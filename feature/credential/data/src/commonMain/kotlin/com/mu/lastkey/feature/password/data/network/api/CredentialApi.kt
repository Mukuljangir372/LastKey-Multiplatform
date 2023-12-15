package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.model.CredentialRealmModel

internal interface CredentialApi {
    suspend fun getCredentialsByOffset(offset: Int, limit: Int): List<CredentialRealmModel>
    suspend fun getCredentialById(id: String): CredentialRealmModel?
    suspend fun insertOrReplace(credential: CredentialRealmModel)
    suspend fun deleteCredentialById(id: String)
}
