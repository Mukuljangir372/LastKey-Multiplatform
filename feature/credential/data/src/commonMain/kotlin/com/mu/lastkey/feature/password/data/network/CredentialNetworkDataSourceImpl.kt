package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.mapper.CredentialMapper
import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.feature.password.data.network.api.CredentialApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class CredentialNetworkDataSourceImpl(
    private val credentialApi: CredentialApi,
    private val dispatcher: CoroutineDispatcher,
    private val credentialMapper: CredentialMapper
) : CredentialNetworkDataSource {
    override suspend fun getCredentialsByOffset(
        offset: Int,
        limit: Int
    ): List<CredentialNetworkModel> {
        return withContext(dispatcher) {
            val result = credentialApi.getCredentialsByOffset(offset = offset, limit = limit)
            result.map { credentialMapper.realmCredentialToNetwork(it) }
        }
    }

    override suspend fun getCredentialById(id: String): CredentialNetworkModel? {
        return withContext(dispatcher) {
            val result = credentialApi.getCredentialById(id)
            result?.let { credentialMapper.realmCredentialToNetwork(it) }
        }
    }

    override suspend fun insertOrReplace(credential: CredentialNetworkModel) {
        withContext(dispatcher) {
            val model = credentialMapper.networkCredentialToRealm(credential)
            credentialApi.insertOrReplace(model)
        }
    }

    override suspend fun deleteCredentialById(id: String) {
        withContext(dispatcher) {
            credentialApi.deleteCredentialById(id)
        }
    }
}
