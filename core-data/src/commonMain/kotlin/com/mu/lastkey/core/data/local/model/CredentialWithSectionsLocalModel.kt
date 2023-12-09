package com.mu.lastkey.core.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class CredentialWithSectionsLocalModel(
    val credential: CredentialLocalModel,
    val fields: List<CredentialSectionLocalModel>
)
