package com.mu.lastkey.core.domain.model.credential

import kotlinx.datetime.LocalDateTime

data class CredentialSection(
    val id: String,
    val name: String,
    val credentialId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
