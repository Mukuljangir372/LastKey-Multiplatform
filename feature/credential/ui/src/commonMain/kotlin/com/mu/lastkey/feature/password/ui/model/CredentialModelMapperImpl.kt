package com.mu.lastkey.feature.password.ui.model

import com.mu.lastkey.core.domain.model.credential.Credential

internal class CredentialModelMapperImpl : CredentialModelMapper {
    override fun credentialToDisplayModel(
        credential: Credential
    ): CredentialDisplayModel {
        return CredentialDisplayModel(
            id = credential.id,
            name = credential.name,
            description = credential.createdAt.toString()
        )
    }
}
