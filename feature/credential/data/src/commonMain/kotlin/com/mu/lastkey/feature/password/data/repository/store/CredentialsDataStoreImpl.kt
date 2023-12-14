package com.mu.lastkey.feature.password.data.repository.store

import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.domain.model.credential.Credential

internal class CredentialsDataStoreImpl : CredentialsDataStore {
    override suspend fun getCredentials(
        offset: Int,
        refresh: Boolean,
        pagingKey: String
    ): ResultWrapper<List<Credential>> {
        TODO()
    }
}
