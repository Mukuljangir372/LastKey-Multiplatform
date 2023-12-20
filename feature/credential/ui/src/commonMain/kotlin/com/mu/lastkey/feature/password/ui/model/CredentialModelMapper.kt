package com.mu.lastkey.feature.password.ui.model

import com.mu.lastkey.core.domain.model.credential.Credential

internal interface CredentialModelMapper {
    fun credentialToDisplayModel(credential: Credential): CredentialDisplayModel
}
