package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.CredentialSectionFieldLocalModel
import com.mu.lastkey.core.data.network.CredentialSectionFieldNetworkModel
import com.mu.lastkey.core.domain.model.credential.CredentialSectionField
import com.mu.lastkey.core.network.realm.model.CredentialSectionFieldRealmModel
import com.mu.lastkey.core.utils.date.toLocalDateTime
import com.mu.lastkey.core.utils.date.toRealmInstant
import commulastkeycoredata.CredentialSectionFieldEntity
import org.mongodb.kbson.ObjectId

internal class CredentialFieldMapperImpl : CredentialFieldMapper {
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
