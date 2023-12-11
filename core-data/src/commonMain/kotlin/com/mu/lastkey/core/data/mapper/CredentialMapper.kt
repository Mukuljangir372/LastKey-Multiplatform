package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionFieldLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionLocalModel
import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.core.data.network.CredentialSectionFieldNetworkModel
import com.mu.lastkey.core.data.network.CredentialSectionNetworkModel
import com.mu.lastkey.core.domain.model.credential.Credential
import com.mu.lastkey.core.domain.model.credential.CredentialSection
import com.mu.lastkey.core.domain.model.credential.CredentialSectionField
import commulastkeycoredata.CredentialEntity
import commulastkeycoredata.CredentialSectionEntity
import commulastkeycoredata.CredentialSectionFieldEntity

interface CredentialMapper {
    fun networkCredentialToLocal(
        model: CredentialNetworkModel
    ): CredentialLocalModel

    fun networkSectionToLocal(
        model: CredentialSectionNetworkModel
    ): CredentialSectionLocalModel

    fun networkFieldToLocal(
        model: CredentialSectionFieldNetworkModel
    ): CredentialSectionFieldLocalModel

    fun entityCredentialToLocal(
        model: CredentialEntity
    ): CredentialLocalModel

    fun entitySectionToLocal(
        model: CredentialSectionEntity,
        fields: List<CredentialSectionFieldLocalModel>
    ): CredentialSectionLocalModel

    fun entityFieldToLocal(
        model: CredentialSectionFieldEntity
    ): CredentialSectionFieldLocalModel

    fun localCredentialToDomain(
        model: CredentialLocalModel
    ): Credential

    fun localSectionToDomain(
        model: CredentialSectionLocalModel
    ): CredentialSection

    fun localFieldToDomain(
        model: CredentialSectionFieldLocalModel
    ): CredentialSectionField
}
