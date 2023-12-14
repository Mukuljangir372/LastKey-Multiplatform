package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.network.CredentialSectionFieldNetworkModel

internal interface CredentialFieldNetworkDataSource {
    suspend fun getFields(sectionId: String): List<CredentialSectionFieldNetworkModel>
    suspend fun getFieldById(id: String): CredentialSectionFieldNetworkModel?
    suspend fun insertOrReplace(field: CredentialSectionFieldNetworkModel)
    suspend fun deleteFieldById(id: String)
}
