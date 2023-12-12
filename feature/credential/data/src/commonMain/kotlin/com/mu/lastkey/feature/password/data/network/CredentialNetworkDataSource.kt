package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.network.realm.model.CredentialRealmModel

internal interface CredentialNetworkDataSource {
    suspend fun createCredential(credential: CredentialRealmModel)
}
