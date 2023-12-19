package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.local.model.CredentialPagingLocalModel
import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.core.domain.model.credential.Credential
import com.mu.lastkey.core.network.realm.model.CredentialRealmModel
import com.mu.lastkey.core.utils.date.toLocalDateTime
import com.mu.lastkey.core.utils.date.toRealmInstant
import commulastkeycoredata.CredentialEntity
import commulastkeycoredata.CredentialPagingEntity
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

    override fun entityCredentialPagingToLocal(
        model: CredentialPagingEntity
    ): CredentialPagingLocalModel {
        return CredentialPagingLocalModel(
            id = model.id,
            pagingKey = model.pagingKey,
            offset = model.offset.toInt(),
            offsetIds = model.offsetIds
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
}
