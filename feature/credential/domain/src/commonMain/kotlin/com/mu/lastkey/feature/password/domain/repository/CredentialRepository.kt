package com.mu.lastkey.feature.password.domain.repository

import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.domain.model.credential.Credential

interface CredentialRepository {
    suspend fun getCredentials(
        offset: Int,
        refresh: Boolean,
        pagingKey: String
    ): ResultWrapper<List<Credential>>
}
