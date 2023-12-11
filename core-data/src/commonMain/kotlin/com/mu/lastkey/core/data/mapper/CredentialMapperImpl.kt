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

internal class CredentialMapperImpl : CredentialMapper {
    override fun networkCredentialToLocal(
        model: CredentialNetworkModel
    ): CredentialLocalModel {
        return CredentialLocalModel(
            id = model.id,
            name = model.name,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun networkSectionToLocal(
        model: CredentialSectionNetworkModel
    ): CredentialSectionLocalModel {
        return CredentialSectionLocalModel(
            id = model.id,
            name = model.name,
            credentialId = model.credentialId,
            fields = model.fields.map { networkFieldToLocal(it) },
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun networkFieldToLocal(
        model: CredentialSectionFieldNetworkModel
    ): CredentialSectionFieldLocalModel {
        return CredentialSectionFieldLocalModel(
            id = model.id,
            sectionId = model.sectionId,
            label = model.label,
            value = model.value,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun entityCredentialToLocal(
        model: CredentialEntity
    ): CredentialLocalModel {
        return CredentialLocalModel(
            id = model.id,
            name = model.name,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun entitySectionToLocal(
        model: CredentialSectionEntity,
        fields: List<CredentialSectionFieldLocalModel>
    ): CredentialSectionLocalModel {
        return CredentialSectionLocalModel(
            id = model.id,
            name = model.name,
            credentialId = model.credential_id,
            fields = fields,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun entityFieldToLocal(
        model: CredentialSectionFieldEntity
    ): CredentialSectionFieldLocalModel {
        return CredentialSectionFieldLocalModel(
            id = model.id,
            sectionId = model.section_id,
            label = model.label,
            value = model.value_,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun localCredentialToDomain(
        model: CredentialLocalModel
    ): Credential {
        return Credential(
            id = model.id,
            name = model.name,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun localSectionToDomain(
        model: CredentialSectionLocalModel
    ): CredentialSection {
        return CredentialSection(
            id = model.id,
            name = model.name,
            credentialId = model.credentialId,
            fields = model.fields.map { localFieldToDomain(it) },
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun localFieldToDomain(
        model: CredentialSectionFieldLocalModel
    ): CredentialSectionField {
        return CredentialSectionField(
            id = model.id,
            sectionId = model.sectionId,
            label = model.label,
            value = model.value,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }
}
