package com.mu.lastkey.core.data.local.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CredentialSectionFieldLocalModel(
    val id: String,
    val label: String,
    val value: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
