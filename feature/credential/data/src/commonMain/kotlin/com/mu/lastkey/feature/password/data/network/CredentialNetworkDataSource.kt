package com.mu.lastkey.feature.password.data.network

import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.core.domain.model.ResultWrapper

internal interface CredentialNetworkDataSource {
    suspend fun getCredentialsByOffset(
        offset: Int,
        limit: Int
    ): ResultWrapper<List<CredentialNetworkModel>>

    suspend fun getCredentialById(
        id: String
    ): ResultWrapper<CredentialNetworkModel>

    suspend fun insertOrReplace(
        credential: CredentialNetworkModel
    ): ResultWrapper<CredentialNetworkModel>

    suspend fun deleteCredentialById(
        id: String
    ): ResultWrapper<String>
}
