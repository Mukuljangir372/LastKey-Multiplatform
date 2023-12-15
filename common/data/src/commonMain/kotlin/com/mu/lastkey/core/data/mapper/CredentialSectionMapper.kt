package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.CredentialSectionLocalModel
import com.mu.lastkey.core.data.local.model.CredentialSectionWithFieldLocalModel
import com.mu.lastkey.core.data.network.CredentialSectionNetworkModel
import com.mu.lastkey.core.domain.model.credential.CredentialSection
import com.mu.lastkey.core.domain.model.credential.CredentialSectionWithField
import com.mu.lastkey.core.network.realm.model.CredentialSectionRealmModel
import commulastkeycoredata.CredentialSectionEntity

interface CredentialSectionMapper {
    fun realmSectionToNetwork(
        model: CredentialSectionRealmModel
    ): CredentialSectionNetworkModel

    fun networkSectionToRealm(
        model: CredentialSectionNetworkModel
    ): CredentialSectionRealmModel

    fun networkSectionToLocal(
        model: CredentialSectionNetworkModel
    ): CredentialSectionLocalModel

    fun entitySectionToLocal(
        model: CredentialSectionEntity
    ): CredentialSectionLocalModel

    fun localSectionToDomain(
        model: CredentialSectionLocalModel
    ): CredentialSection

    fun localSectionWithFieldToDomain(
        model: CredentialSectionWithFieldLocalModel
    ): CredentialSectionWithField
}
