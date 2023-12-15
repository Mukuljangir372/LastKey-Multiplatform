package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.CredentialSectionFieldLocalModel
import com.mu.lastkey.core.data.network.CredentialSectionFieldNetworkModel
import com.mu.lastkey.core.domain.model.credential.CredentialSectionField
import com.mu.lastkey.core.network.realm.model.CredentialSectionFieldRealmModel
import commulastkeycoredata.CredentialSectionFieldEntity

interface CredentialFieldMapper {
    fun realmFieldToNetwork(
        model: CredentialSectionFieldRealmModel
    ): CredentialSectionFieldNetworkModel

    fun networkFieldToRealm(
        model: CredentialSectionFieldNetworkModel
    ): CredentialSectionFieldRealmModel

    fun networkFieldToLocal(
        model: CredentialSectionFieldNetworkModel
    ): CredentialSectionFieldLocalModel

    fun entityFieldToLocal(
        model: CredentialSectionFieldEntity
    ): CredentialSectionFieldLocalModel

    fun localFieldToDomain(
        model: CredentialSectionFieldLocalModel
    ): CredentialSectionField
}
