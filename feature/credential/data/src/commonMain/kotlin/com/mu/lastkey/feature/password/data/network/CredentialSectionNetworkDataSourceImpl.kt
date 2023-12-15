package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.mapper.CredentialSectionMapper
import com.mu.lastkey.core.data.network.CredentialSectionNetworkModel
import com.mu.lastkey.feature.password.data.network.api.CredentialSectionApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class CredentialSectionNetworkDataSourceImpl(
    private val sectionApi: CredentialSectionApi,
    private val dispatcher: CoroutineDispatcher,
    private val mapper: CredentialSectionMapper
) : CredentialSectionNetworkDataSource {
    override suspend fun getSections(credentialId: String): List<CredentialSectionNetworkModel> {
        return withContext(dispatcher) {
            val result = sectionApi.getSections(credentialId)
            result.map { mapper.realmSectionToNetwork(it) }
        }
    }

    override suspend fun getSectionById(id: String): CredentialSectionNetworkModel? {
        return withContext(dispatcher) {
            val result = sectionApi.getSectionById(id)
            result?.let { mapper.realmSectionToNetwork(it) }
        }
    }

    override suspend fun insertOrReplace(section: CredentialSectionNetworkModel) {
        withContext(dispatcher) {
            val model = mapper.networkSectionToRealm(section)
            sectionApi.insertOrReplace(model)
        }
    }

    override suspend fun deleteSectionById(id: String) {
        withContext(dispatcher) {
            sectionApi.deleteSectionById(id)
        }
    }
}
