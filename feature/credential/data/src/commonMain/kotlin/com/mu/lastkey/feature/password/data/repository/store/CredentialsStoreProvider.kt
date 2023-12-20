package com.mu.lastkey.feature.password.data.repository.store

import com.mu.lastkey.core.data.local.CredentialLocalDataSource
import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.local.model.CredentialPagingLocalModel
import com.mu.lastkey.core.data.mapper.CredentialMapper
import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.domain.model.credential.Credential
import com.mu.lastkey.core.utils.storeBuilder
import com.mu.lastkey.core.utils.useDispatchers
import com.mu.lastkey.core.utils.uuid.UUIDGenerator
import com.mu.lastkey.feature.password.data.network.CredentialNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store

internal class CredentialsStoreProvider(
    private val networkDataSource: CredentialNetworkDataSource,
    private val localDataSource: CredentialLocalDataSource,
    private val mapper: CredentialMapper,
    private val dispatcher: CoroutineDispatcher,
    private val uuidGenerator: UUIDGenerator
) : Store<CredentialsStoreProvider.Key, List<Credential>> by storeBuilder(
    fetcher = getFetcher(networkDataSource),
    sourceOfTruth = getSourceOfTruth(
        localDataSource = localDataSource,
        mapper = mapper,
        dispatcher = dispatcher,
        uuidGenerator = uuidGenerator
    )
).build() {
    data class Key(val offset: Int, val pagingKey: String)

    companion object {
        private const val LIMIT = 40

        private fun getFetcher(
            networkDataSource: CredentialNetworkDataSource
        ): Fetcher<Key, List<CredentialNetworkModel>> {
            return Fetcher.of { key: Key ->
                val result = networkDataSource.getCredentialsByOffset(
                    offset = key.offset,
                    limit = LIMIT
                )
                check(result is ResultWrapper.Success)
                result.data.orEmpty()
            }
        }

        private fun getSourceOfTruth(
            localDataSource: CredentialLocalDataSource,
            mapper: CredentialMapper,
            dispatcher: CoroutineDispatcher,
            uuidGenerator: UUIDGenerator
        ): SourceOfTruth<Key, List<CredentialNetworkModel>, List<Credential>> {
            return SourceOfTruth.of<Key, List<CredentialNetworkModel>, List<Credential>>(
                reader = { key: Key ->
                    observeCredentials(
                        offset = key.offset,
                        localDataSource = localDataSource,
                        mapper = mapper,
                        dispatcher = dispatcher
                    )
                },
                writer = { key: Key, network ->
                    deleteCredentialsByPaging(
                        offset = key.offset,
                        pagingKey = key.pagingKey,
                        localDataSource = localDataSource
                    )
                    insertCredentials(
                        offset = key.offset,
                        pagingKey = key.pagingKey,
                        credentials = network,
                        localDataSource = localDataSource,
                        uuidGenerator = uuidGenerator,
                        mapper = mapper
                    )
                },
                delete = { key: Key ->
                    deleteCredentialsByPaging(
                        offset = key.offset,
                        pagingKey = key.pagingKey,
                        localDataSource = localDataSource
                    )
                },
                deleteAll = null
            ).useDispatchers(dispatcher)
        }

        private fun observeCredentials(
            offset: Int,
            localDataSource: CredentialLocalDataSource,
            mapper: CredentialMapper,
            dispatcher: CoroutineDispatcher
        ): Flow<List<Credential>> {
            return localDataSource.observeCredentialsByOffset(
                limit = LIMIT,
                offset = offset
            ).map { list ->
                list.map { mapper.localCredentialToDomain(it) }
            }.flowOn(dispatcher)
        }

        private suspend fun deleteCredentialsByPaging(
            offset: Int,
            pagingKey: String,
            localDataSource: CredentialLocalDataSource
        ): List<String> {
            val paging = localDataSource.getPaging(pagingKey, offset) ?: return emptyList()
            val ids = paging.offsetIds
            localDataSource.deleteCredentialsById(ids)
            return ids
        }

        private suspend fun insertCredentials(
            offset: Int,
            pagingKey: String,
            credentials: List<CredentialNetworkModel>,
            localDataSource: CredentialLocalDataSource,
            uuidGenerator: UUIDGenerator,
            mapper: CredentialMapper
        ): List<CredentialLocalModel> {
            val localCredentials = credentials.map { mapper.networkCredentialToLocal(it) }
            val credentialIds = localCredentials.map { it.id }
            insertPaging(
                offset = offset,
                pagingKey = pagingKey,
                ids = credentialIds,
                localDataSource = localDataSource,
                uuidGenerator = uuidGenerator
            )
            localDataSource.insertCredentials(localCredentials)
            return localCredentials
        }

        private suspend fun insertPaging(
            offset: Int,
            pagingKey: String,
            ids: List<String>,
            localDataSource: CredentialLocalDataSource,
            uuidGenerator: UUIDGenerator
        ): List<String> {
            var paging = localDataSource.getPaging(pagingKey, offset)
            if (paging == null) {
                paging = createPaging(
                    uuidGenerator = uuidGenerator,
                    pagingKey = pagingKey,
                    offset = offset
                )
            }
            localDataSource.insertPaging(paging.copy(offsetIds = ids))
            return ids
        }

        private fun createPaging(
            uuidGenerator: UUIDGenerator,
            pagingKey: String,
            offset: Int
        ): CredentialPagingLocalModel {
            return CredentialPagingLocalModel(
                id = uuidGenerator.generate(),
                pagingKey = pagingKey,
                offset = offset,
                offsetIds = emptyList()
            )
        }
    }
}
