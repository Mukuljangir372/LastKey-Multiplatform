package com.mu.lastkey.core.data.local

import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionFieldLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionWithFieldLocalModel
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
    ): Flow<List<CredentialSectionWithFieldLocalModel>>

    suspend fun getCredentialById(id: String): CredentialLocalModel?
    suspend fun insertCredentials(list: List<CredentialLocalModel>)
    suspend fun insertSections(list: List<CredentialSectionLocalModel>)
    suspend fun insertFields(list: List<CredentialSectionFieldLocalModel>)
    suspend fun deleteCredentialsById(id: List<String>)
    suspend fun deleteSectionsById(id: List<String>)
    suspend fun deleteFieldsById(id: List<String>)
}
