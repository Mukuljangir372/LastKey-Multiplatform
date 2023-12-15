package com.mu.lastkey.core.domain.model.credential

import kotlinx.datetime.LocalDateTime

data class CredentialSectionWithField(
    val id: String,
    val name: String,
    val credentialId: String,
    val fields: List<CredentialSectionField>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
