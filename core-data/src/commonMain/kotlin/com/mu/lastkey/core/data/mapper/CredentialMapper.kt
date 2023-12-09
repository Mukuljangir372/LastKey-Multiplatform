package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionLocalModel
import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.core.data.network.CredentialSectionNetworkModel

interface CredentialMapper {
    fun networkCredentialToLocal(model: CredentialNetworkModel): CredentialLocalModel
    fun networkSectionToLocal(model: CredentialSectionNetworkModel): CredentialSectionLocalModel
}
