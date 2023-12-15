package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.CredentialSectionLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionWithFieldLocalModel
import com.mu.lastkey.core.data.network.CredentialSectionNetworkModel
import com.mu.lastkey.core.domain.model.credential.CredentialSection
import com.mu.lastkey.core.domain.model.credential.CredentialSectionWithField
import com.mu.lastkey.core.network.realm.model.CredentialSectionRealmModel
import com.mu.lastkey.core.utils.date.toLocalDateTime
import com.mu.lastkey.core.utils.date.toRealmInstant
import commulastkeycoredata.CredentialSectionEntity
import org.mongodb.kbson.ObjectId

internal class CredentialSectionMapperImpl(
    private val fieldMapper: CredentialFieldMapper
) : CredentialSectionMapper {
    override fun realmSectionToNetwork(
        model: CredentialSectionRealmModel
    ): CredentialSectionNetworkModel {
        return CredentialSectionNetworkModel(
            id = model._id.toString(),
            name = model.name,
            credentialId = model.credentialId,
            createdAt = model.createdAt.toLocalDateTime(),
            updatedAt = model.updatedAt.toLocalDateTime()
        )
    }

    override fun networkSectionToRealm(
        model: CredentialSectionNetworkModel
    ): CredentialSectionRealmModel {
        return CredentialSectionRealmModel().apply {
            _id = ObjectId(model.id)
            name = model.name
            credentialId = model.credentialId
            createdAt = model.createdAt.toRealmInstant()
            updatedAt = model.updatedAt.toRealmInstant()
        }
    }

    override fun networkSectionToLocal(
        model: CredentialSectionNetworkModel
    ): CredentialSectionLocalModel {
        return CredentialSectionLocalModel(
            id = model.id,
            name = model.name,
            credentialId = model.credentialId,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun entitySectionToLocal(
        model: CredentialSectionEntity
    ): CredentialSectionLocalModel {
        return CredentialSectionLocalModel(
            id = model.id,
            name = model.name,
            credentialId = model.credential_id,
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
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }

    override fun localSectionWithFieldToDomain(
        model: CredentialSectionWithFieldLocalModel
    ): CredentialSectionWithField {
        return CredentialSectionWithField(
            section = localSectionToDomain(model.section),
            fields = model.fields.map { fieldMapper.localFieldToDomain(it) }
        )
    }
}
