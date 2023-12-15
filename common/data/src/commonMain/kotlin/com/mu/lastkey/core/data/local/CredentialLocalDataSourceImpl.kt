package com.mu.lastkey.core.data.local

import app.cash.sqldelight.coroutines.asFlow
import com.mu.lastkey.core.data.AppDatabase
import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.mapper.CredentialMapper
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

    override suspend fun deleteCredentialsById(id: List<String>) {
        database.credentialQueries.deleteCredentialsById(id)
    }
}
