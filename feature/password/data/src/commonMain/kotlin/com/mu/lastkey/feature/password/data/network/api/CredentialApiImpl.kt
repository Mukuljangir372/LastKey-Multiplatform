package com.mu.lastkey.feature.password.data.network.api

import com.mu.lastkey.core.network.realm.RealmClient
import com.mu.lastkey.core.network.realm.model.CredentialRealmModel

internal class CredentialApiImpl(
    private val realmClient: RealmClient
) : CredentialApi {
    override suspend fun create(credential: CredentialRealmModel) {
        TODO("Not yet implemented")
    }
}
