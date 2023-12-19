package com.mu.lastkey.core.data.local

import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.local.model.CredentialPagingLocalModel
import kotlinx.coroutines.flow.Flow

interface CredentialLocalDataSource {
    fun observeCredentialsByOffset(
        limit: Int,
        offset: Int
    ): Flow<List<CredentialLocalModel>>

    suspend fun getCredentialById(id: String): CredentialLocalModel?
    suspend fun getPaging(pagingKey: String, offset: Int): CredentialPagingLocalModel?
    suspend fun insertCredentials(list: List<CredentialLocalModel>)
    suspend fun insertPaging(model: CredentialPagingLocalModel)
    suspend fun deleteCredentialsById(id: List<String>)
}
