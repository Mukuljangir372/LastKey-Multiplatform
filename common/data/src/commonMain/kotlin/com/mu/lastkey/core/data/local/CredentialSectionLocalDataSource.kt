package com.mu.lastkey.core.data.local

import com.mu.lastkey.core.data.local.model.CredentialSectionLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionWithFieldLocalModel
import kotlinx.coroutines.flow.Flow

interface CredentialSectionLocalDataSource {
    suspend fun observeSectionsByOffset(
        credentialId: String,
        limit: Long,
        offset: Long
    ): Flow<List<CredentialSectionWithFieldLocalModel>>

    suspend fun insertSections(list: List<CredentialSectionLocalModel>)
    suspend fun deleteSectionsById(id: List<String>)
}
