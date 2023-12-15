package com.mu.lastkey.core.data.local

import app.cash.sqldelight.coroutines.asFlow
import com.mu.lastkey.core.data.AppDatabase
import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionFieldLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionWithFieldLocalModel
import com.mu.lastkey.core.data.mapper.CredentialMapper
import commulastkeycoredata.GetCredentialSectionsByOffset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CredentialLocalDataSourceImpl(
    private val database: AppDatabase,
    private val mapper: CredentialMapper
) : CredentialLocalDataSource {
    override suspend fun observeCredentialsByOffset(
        limit: Long,
        offset: Long
    ): Flow<List<CredentialLocalModel>> {
        return database.credentialQueries
            .getCredentialsByOffset(limit, offset).asFlow()
            .map { query ->
                query.executeAsList().map { mapper.entityCredentialToLocal(it) }
            }
    }

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

    override suspend fun getCredentialById(id: String): CredentialLocalModel? {
        val query = database.credentialQueries.getCredentialById(id)
        return query.executeAsOneOrNull()?.let { mapper.entityCredentialToLocal(it) }
    }

    override suspend fun insertCredentials(list: List<CredentialLocalModel>) {
        list.forEach { model ->
            database.credentialQueries.insertCredentials(
                id = model.id,
                name = model.name,
                createdAt = model.createdAt,
                updatedAt = model.updatedAt
            )
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

    override suspend fun deleteCredentialsById(id: List<String>) {
        database.credentialQueries.deleteCredentialsById(id)
    }

    override suspend fun deleteSectionsById(id: List<String>) {
        database.credentialQueries.deleteCredentialSectionsById(id)
    }

    override suspend fun deleteFieldsById(id: List<String>) {
        database.credentialQueries.deleteCredentialSectionFieldsById(id)
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
                    id = section.id,
                    name = section.name,
                    credentialId = section.credential_id,
                    fields = fields,
                    createdAt = section.createdAt,
                    updatedAt = section.updatedAt
                )
            }
        }
    }
}
