package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.network.CredentialSectionNetworkModel

internal interface CredentialSectionNetworkDataSource {
    suspend fun getSections(credentialId: String): List<CredentialSectionNetworkModel>
    suspend fun getSectionById(id: String): CredentialSectionNetworkModel?
    suspend fun insertOrReplace(section: CredentialSectionNetworkModel)
    suspend fun deleteSectionById(id: String)
}
