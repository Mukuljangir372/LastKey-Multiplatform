package com.mu.lastkey.core.data.local

import app.cash.sqldelight.coroutines.asFlow
import com.mu.lastkey.core.data.AppDatabase
import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.local.model.CredentialPagingLocalModel
import com.mu.lastkey.core.data.mapper.CredentialMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CredentialLocalDataSourceImpl(
    private val database: AppDatabase,
    private val mapper: CredentialMapper
) : CredentialLocalDataSource {
    override fun observeCredentialsByOffset(
        limit: Int,
        offset: Int
    ): Flow<List<CredentialLocalModel>> {
        return database.credentialQueries
            .getCredentialsByOffset(limit.toLong(), offset.toLong()).asFlow()
            .map { query ->
                query.executeAsList().map { mapper.entityCredentialToLocal(it) }
            }
    }

    override suspend fun getCredentialById(id: String): CredentialLocalModel? {
        val query = database.credentialQueries.getCredentialById(id)
        return query.executeAsOneOrNull()?.let { mapper.entityCredentialToLocal(it) }
    }

    override suspend fun getPaging(pagingKey: String, offset: Int): CredentialPagingLocalModel? {
        val query =
            database.credentialQueries.getCredentialPagingByOffset(pagingKey, offset.toLong())
        return query.executeAsOneOrNull()?.let { mapper.entityCredentialPagingToLocal(it) }
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

    override suspend fun insertPaging(model: CredentialPagingLocalModel) {
        database.credentialQueries.insertCredentialPaging(
            id = model.id,
            pagingKey = model.pagingKey,
            offset = model.offset.toLong(),
            offsetIds = model.offsetIds
        )
    }

    override suspend fun deleteCredentialsById(id: List<String>) {
        database.credentialQueries.deleteCredentialsById(id)
    }
}
