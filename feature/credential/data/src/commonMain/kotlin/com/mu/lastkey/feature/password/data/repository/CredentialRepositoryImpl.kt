package com.mu.lastkey.feature.password.data.repository

import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.domain.model.credential.Credential
import com.mu.lastkey.feature.password.data.repository.store.CredentialsDataStore
import com.mu.lastkey.feature.password.domain.repository.CredentialRepository

internal class CredentialRepositoryImpl(
    private val credentialsDataStore: CredentialsDataStore
) : CredentialRepository {
    override suspend fun getCredentials(
        offset: Int,
        refresh: Boolean,
        pagingKey: String
    ): ResultWrapper<List<Credential>> {
        return credentialsDataStore.getCredentials(
            offset = offset,
            refresh = refresh,
            pagingKey = pagingKey
        )
    }
}
