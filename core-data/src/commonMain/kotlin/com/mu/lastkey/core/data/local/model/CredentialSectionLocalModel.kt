package com.mu.lastkey.core.data.local.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CredentialSectionLocalModel(
    val id: String,
    val name: String,
    val fields: List<CredentialSectionFieldLocalModel>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
