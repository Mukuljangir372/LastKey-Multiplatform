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
import commulastkeycoredata.CredentialEntity
import commulastkeycoredata.CredentialSectionEntity
import commulastkeycoredata.CredentialSectionFieldEntity

interface CredentialMapper {
    fun realmCredentialToNetwork(
        model: CredentialRealmModel
    ): CredentialNetworkModel

    fun realmSectionToNetwork(
        model: CredentialSectionRealmModel
    ): CredentialSectionNetworkModel

    fun realmFieldToNetwork(
        model: CredentialSectionFieldRealmModel
    ): CredentialSectionFieldNetworkModel

    fun networkCredentialToRealm(
        model: CredentialNetworkModel
    ): CredentialRealmModel

    fun networkSectionToRealm(
        model: CredentialSectionNetworkModel
    ): CredentialSectionRealmModel

    fun networkFieldToRealm(
        model: CredentialSectionFieldNetworkModel
    ): CredentialSectionFieldRealmModel

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
