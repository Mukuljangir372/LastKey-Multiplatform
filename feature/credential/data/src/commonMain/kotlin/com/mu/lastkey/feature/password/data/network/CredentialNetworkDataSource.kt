package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.core.data.network.CredentialSectionFieldNetworkModel
import com.mu.lastkey.core.data.network.CredentialSectionNetworkModel

internal interface CredentialNetworkDataSource {
    suspend fun getCredentialsByOffset(offset: Int, limit: Int): List<CredentialNetworkModel>
    suspend fun getSections(credentialId: String): List<CredentialSectionNetworkModel>
    suspend fun getFields(sectionId: String): List<CredentialSectionFieldNetworkModel>
    suspend fun getCredentialById(id: String): CredentialNetworkModel?
    suspend fun getSectionById(id: String): CredentialSectionNetworkModel?
    suspend fun getFieldById(id: String): CredentialSectionFieldNetworkModel?
    suspend fun insertOrReplace(credential: CredentialNetworkModel)
    suspend fun insertOrReplace(section: CredentialSectionNetworkModel)
    suspend fun insertOrReplace(field: CredentialSectionFieldNetworkModel)
    suspend fun deleteCredentialById(id: String)
    suspend fun deleteSectionById(id: String)
    suspend fun deleteFieldById(id: String)
}
