package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.mapper.CredentialMapper
import com.mu.lastkey.core.data.network.CredentialSectionFieldNetworkModel
import com.mu.lastkey.feature.password.data.network.api.CredentialFieldApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class CredentialFieldNetworkDataSourceImpl(
    private val fieldApi: CredentialFieldApi,
    private val dispatcher: CoroutineDispatcher,
    private val credentialMapper: CredentialMapper
) : CredentialFieldNetworkDataSource {
    override suspend fun getFields(sectionId: String): List<CredentialSectionFieldNetworkModel> {
        return withContext(dispatcher) {
            val result = fieldApi.getFields(sectionId)
            result.map { credentialMapper.realmFieldToNetwork(it) }
        }
    }

    override suspend fun getFieldById(id: String): CredentialSectionFieldNetworkModel? {
        return withContext(dispatcher) {
            val result = fieldApi.getFieldById(id)
            result?.let { credentialMapper.realmFieldToNetwork(it) }
        }
    }

    override suspend fun insertOrReplace(field: CredentialSectionFieldNetworkModel) {
        withContext(dispatcher) {
            val model = credentialMapper.networkFieldToRealm(field)
            fieldApi.insertOrReplace(model)
        }
    }

    override suspend fun deleteFieldById(id: String) {
        withContext(dispatcher) {
            fieldApi.deleteFieldById(id)
        }
    }
}
