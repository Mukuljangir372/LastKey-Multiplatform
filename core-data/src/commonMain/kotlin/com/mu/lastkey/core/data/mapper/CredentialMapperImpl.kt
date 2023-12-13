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
import com.mu.lastkey.core.network.realm.model.CredentialRealmModel
import com.mu.lastkey.core.network.realm.model.CredentialSectionFieldRealmModel
import com.mu.lastkey.core.network.realm.model.CredentialSectionRealmModel
import com.mu.lastkey.core.utils.date.toLocalDateTime
import com.mu.lastkey.core.utils.date.toRealmInstant
import commulastkeycoredata.CredentialEntity
import commulastkeycoredata.CredentialSectionEntity
import commulastkeycoredata.CredentialSectionFieldEntity
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

internal class CredentialMapperImpl : CredentialMapper {
    override fun realmCredentialToNetwork(
        model: CredentialRealmModel
    ): CredentialNetworkModel {
        return CredentialNetworkModel(
            id = model._id.toString(),
            name = model.name,
            createdAt = model.createdAt.toLocalDateTime(),
            updatedAt = model.updatedAt.toLocalDateTime()
        )
    }

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

    override fun realmFieldToNetwork(
        model: CredentialSectionFieldRealmModel
    ): CredentialSectionFieldNetworkModel {
        return CredentialSectionFieldNetworkModel(
            id = model._id.toString(),
            sectionId = model.sectionId,
            label = model.label,
            value = model.value,
            createdAt = model.createdAt.toLocalDateTime(),
            updatedAt = model.updatedAt.toLocalDateTime()
        )
    }

    override fun networkCredentialToRealm(
        model: CredentialNetworkModel
    ): CredentialRealmModel {
        return CredentialRealmModel().apply {
            _id = ObjectId(model.id)
            name = model.name
            createdAt = model.createdAt.toRealmInstant()
            updatedAt = model.updatedAt.toRealmInstant()
        }
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

    override fun networkFieldToRealm(
        model: CredentialSectionFieldNetworkModel
    ): CredentialSectionFieldRealmModel {
        return CredentialSectionFieldRealmModel().apply {
            _id = ObjectId(model.id)
            label = model.label
            value = model.value
            sectionId = model.sectionId
            createdAt = model.createdAt.toRealmInstant()
            updatedAt = model.updatedAt.toRealmInstant()
        }
    }

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
            createdAt = model.createdAt,
            updatedAt = model.updatedAt,
            fields = emptyList()
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
