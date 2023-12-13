package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.mapper.CredentialMapper
import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.core.data.network.CredentialSectionFieldNetworkModel
import com.mu.lastkey.core.data.network.CredentialSectionNetworkModel
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

    override suspend fun getSections(credentialId: String): List<CredentialSectionNetworkModel> {
        return withContext(dispatcher) {
            val result = credentialApi.getSections(credentialId)
            result.map { credentialMapper.realmSectionToNetwork(it) }
        }
    }

    override suspend fun getFields(sectionId: String): List<CredentialSectionFieldNetworkModel> {
        return withContext(dispatcher) {
            val result = credentialApi.getFields(sectionId)
            result.map { credentialMapper.realmFieldToNetwork(it) }
        }
    }

    override suspend fun getCredentialById(id: String): CredentialNetworkModel? {
        return withContext(dispatcher) {
            val result = credentialApi.getCredentialById(id)
            result?.let { credentialMapper.realmCredentialToNetwork(it) }
        }
    }

    override suspend fun getSectionById(id: String): CredentialSectionNetworkModel? {
        return withContext(dispatcher) {
            val result = credentialApi.getSectionById(id)
            result?.let { credentialMapper.realmSectionToNetwork(it) }
        }
    }

    override suspend fun getFieldById(id: String): CredentialSectionFieldNetworkModel? {
        return withContext(dispatcher) {
            val result = credentialApi.getFieldById(id)
            result?.let { credentialMapper.realmFieldToNetwork(it) }
        }
    }

    override suspend fun insertOrReplace(credential: CredentialNetworkModel) {
        withContext(dispatcher) {
            val model = credentialMapper.networkCredentialToRealm(credential)
            credentialApi.insertOrReplace(model)
        }
    }

    override suspend fun insertOrReplace(section: CredentialSectionNetworkModel) {
        withContext(dispatcher) {
            val model = credentialMapper.networkSectionToRealm(section)
            credentialApi.insertOrReplace(model)
        }
    }

    override suspend fun insertOrReplace(field: CredentialSectionFieldNetworkModel) {
        withContext(dispatcher) {
            val model = credentialMapper.networkFieldToRealm(field)
            credentialApi.insertOrReplace(model)
        }
    }

    override suspend fun deleteCredentialById(id: String) {
        withContext(dispatcher) {
            credentialApi.deleteCredentialById(id)
        }
    }

    override suspend fun deleteSectionById(id: String) {
        withContext(dispatcher) {
            credentialApi.deleteSectionById(id)
        }
    }

    override suspend fun deleteFieldById(id: String) {
        withContext(dispatcher) {
            credentialApi.deleteFieldById(id)
        }
    }
}
