package com.mu.lastkey.core.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class CredentialSectionWithFieldLocalModel(
    val section: CredentialSectionLocalModel,
    val fields: List<CredentialSectionFieldLocalModel>
)
