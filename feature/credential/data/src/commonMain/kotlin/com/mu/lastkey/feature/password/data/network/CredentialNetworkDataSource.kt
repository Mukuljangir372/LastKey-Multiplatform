package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.network.CredentialNetworkModel

internal interface CredentialNetworkDataSource {
    suspend fun getCredentialsByOffset(offset: Int, limit: Int): List<CredentialNetworkModel>
    suspend fun getCredentialById(id: String): CredentialNetworkModel?
    suspend fun insertOrReplace(credential: CredentialNetworkModel)
    suspend fun deleteCredentialById(id: String)
}
