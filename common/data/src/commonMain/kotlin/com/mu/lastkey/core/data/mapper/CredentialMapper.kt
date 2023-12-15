package com.mu.lastkey.core.data.mapper

import com.mu.lastkey.core.data.local.model.CredentialLocalModel
import com.mu.lastkey.core.data.network.CredentialNetworkModel
import com.mu.lastkey.core.domain.model.credential.Credential
import com.mu.lastkey.core.network.realm.model.CredentialRealmModel
import commulastkeycoredata.CredentialEntity

interface CredentialMapper {
    fun realmCredentialToNetwork(
        model: CredentialRealmModel
    ): CredentialNetworkModel

    fun networkCredentialToRealm(
        model: CredentialNetworkModel
    ): CredentialRealmModel

    fun networkCredentialToLocal(
        model: CredentialNetworkModel
    ): CredentialLocalModel

    fun entityCredentialToLocal(
        model: CredentialEntity
    ): CredentialLocalModel

    fun localCredentialToDomain(
        model: CredentialLocalModel
    ): Credential
}
