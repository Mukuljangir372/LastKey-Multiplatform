package com.mu.lastkey.core.data.local.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CredentialSectionWithFieldLocalModel(
    val id: String,
    val name: String,
    val credentialId: String,
    val fields: List<CredentialSectionFieldLocalModel>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
