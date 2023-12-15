package com.mu.lastkey.core.data.local

import com.mu.lastkey.core.data.AppDatabase
import com.mu.lastkey.core.data.local.model.CredentialSectionFieldLocalModel

internal class CredentialFieldLocalDataSourceImpl(
    private val database: AppDatabase
) : CredentialFieldLocalDataSource {
    override suspend fun insertFields(list: List<CredentialSectionFieldLocalModel>) {
        list.forEach { model ->
            database.credentialQueries.insertCredentialSectionFields(
                id = model.id,
                value_ = model.value,
                label = model.label,
                section_id = model.sectionId,
                createdAt = model.createdAt,
                updatedAt = model.updatedAt
            )
        }
    }

    override suspend fun deleteFieldsById(id: List<String>) {
        database.credentialQueries.deleteCredentialSectionFieldsById(id)
    }
}
