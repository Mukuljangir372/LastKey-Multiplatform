package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.mapper.CredentialMapper
import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.feature.password.data.network.api.CredentialApi
import com.mu.lastkey.feature.password.data.network.exception.CredentialNotFoundException
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
    ): ResultWrapper<List<CredentialNetworkModel>> {
        return withContext(dispatcher) {
            try {
                val result = credentialApi.getCredentialsByOffset(offset = offset, limit = limit)
                val list = result.map { credentialMapper.realmCredentialToNetwork(it) }
                ResultWrapper.Success(data = list)
            } catch (e: Exception) {
                ResultWrapper.Failure(throwable = e, message = e.message.orEmpty())
            }
        }
    }

    override suspend fun getCredentialById(
        id: String
    ): ResultWrapper<CredentialNetworkModel> {
        return withContext(dispatcher) {
            try {
                val result = credentialApi.getCredentialById(id)
                val credential = result?.let { credentialMapper.realmCredentialToNetwork(it) }
                    ?: throw CredentialNotFoundException(id)
                ResultWrapper.Success(data = credential)
            } catch (e: Exception) {
                ResultWrapper.Failure(throwable = e, message = e.message.orEmpty())
            }
        }
    }

    override suspend fun insertOrReplace(
        credential: CredentialNetworkModel
    ): ResultWrapper<CredentialNetworkModel> {
        return withContext(dispatcher) {
            try {
                val model = credentialMapper.networkCredentialToRealm(credential)
                credentialApi.insertOrReplace(model)
                ResultWrapper.Success(data = credential)
            } catch (e: Exception) {
                ResultWrapper.Failure(throwable = e, message = e.message.orEmpty())
            }
        }
    }

    override suspend fun deleteCredentialById(id: String): ResultWrapper<String> {
        return withContext(dispatcher) {
            try {
                credentialApi.deleteCredentialById(id)
                ResultWrapper.Success(id)
            } catch (e: Exception) {
                ResultWrapper.Failure(throwable = e, message = e.message.orEmpty())
            }
        }
    }
}
