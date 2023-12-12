package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.model.CredentialRealmModel

internal interface CredentialApi {
    suspend fun create(credential: CredentialRealmModel)
}
