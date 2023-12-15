package com.mu.lastkey.core.data.local

import com.mu.lastkey.core.data.local.model.CredentialSectionFieldLocalModel

interface CredentialFieldLocalDataSource {
    suspend fun insertFields(list: List<CredentialSectionFieldLocalModel>)
    suspend fun deleteFieldsById(id: List<String>)
}
