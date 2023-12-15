package com.mu.lastkey.core.data.local

import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import kotlinx.coroutines.flow.Flow

interface CredentialLocalDataSource {
    suspend fun observeCredentialsByOffset(
        limit: Long,
        offset: Long
    ): Flow<List<CredentialLocalModel>>

    suspend fun getCredentialById(id: String): CredentialLocalModel?
    suspend fun insertCredentials(list: List<CredentialLocalModel>)
    suspend fun deleteCredentialsById(id: List<String>)
}
