package com.mu.lastkey.core.domain.model.credential

import kotlinx.datetime.LocalDateTime

data class CredentialSection(
    val id: String,
    val fields: List<CredentialSectionField>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
