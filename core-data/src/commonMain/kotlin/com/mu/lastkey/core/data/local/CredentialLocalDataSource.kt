package com.mu.lastkey.core.data.local

import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionFieldLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionLocalModel
import com.mu.lastkey.core.data.local.model.CredentialWithSectionsLocalModel
import kotlinx.coroutines.flow.Flow

interface CredentialLocalDataSource {
    suspend fun observeCredentialsByOffset(
        limit: Long,
        offset: Long
    ): Flow<List<CredentialLocalModel>>

    suspend fun observeSectionsByOffset(
        credentialId: String,
        limit: Long,
        offset: Long
    ): Flow<List<CredentialSectionLocalModel>>

    suspend fun getCredentialById(id: String): CredentialLocalModel?
    suspend fun getCredentialWithSections(id: String): CredentialWithSectionsLocalModel?
    suspend fun insertCredentials(list: List<CredentialLocalModel>)
    suspend fun insertSection(model: CredentialSectionLocalModel)
    suspend fun insertField(model: CredentialSectionFieldLocalModel)
    suspend fun deleteCredentialById(id: String)
    suspend fun deleteSectionById(id: String)
    suspend fun deleteFieldById(id: String)
}
