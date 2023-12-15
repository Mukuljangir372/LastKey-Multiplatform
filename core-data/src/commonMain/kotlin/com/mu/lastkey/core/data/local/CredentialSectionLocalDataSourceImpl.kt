package com.mu.lastkey.core.data.local

import app.cash.sqldelight.coroutines.asFlow
import com.mu.lastkey.core.data.AppDatabase
import com.mu.lastkey.core.data.local.model.CredentialSectionFieldLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionWithFieldLocalModel
import commulastkeycoredata.GetCredentialSectionsByOffset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CredentialSectionLocalDataSourceImpl(
    private val database: AppDatabase
) : CredentialSectionLocalDataSource {

    override suspend fun observeSectionsByOffset(
        credentialId: String,
        limit: Long,
        offset: Long
    ): Flow<List<CredentialSectionWithFieldLocalModel>> {
        return database.credentialQueries
            .getCredentialSectionsByOffset(credentialId, limit, offset)
            .asFlow()
            .map { query ->
                mapCredentialSections(query.executeAsList())
            }
    }

    override suspend fun insertSections(list: List<CredentialSectionLocalModel>) {
        list.forEach { model ->
            database.credentialQueries.insertCredentialSections(
                id = model.id,
                name = model.name,
                credential_id = model.credentialId,
                createdAt = model.createdAt,
                updatedAt = model.updatedAt
            )
        }
    }

    override suspend fun deleteSectionsById(id: List<String>) {
        database.credentialQueries.deleteCredentialSectionsById(id)
    }

    companion object {
        private fun mapCredentialSections(
            result: List<GetCredentialSectionsByOffset>
        ): List<CredentialSectionWithFieldLocalModel> {
            return result.groupBy { it.id }.map { (_, list) ->
                val section = list.first()
                val fields = list.map { field ->
                    CredentialSectionFieldLocalModel(
                        id = field.field_id,
                        label = field.field_label,
                        sectionId = field.field_section_id,
                        value = field.field_value,
                        createdAt = field.field_createdAt,
                        updatedAt = field.field_updatedAt
                    )
                }
                CredentialSectionWithFieldLocalModel(
                    section = CredentialSectionLocalModel(
                        id = section.id,
                        name = section.name,
                        credentialId = section.credential_id,
                        createdAt = section.createdAt,
                        updatedAt = section.updatedAt
                    ),
                    fields = fields
                )
            }
        }
    }
}
