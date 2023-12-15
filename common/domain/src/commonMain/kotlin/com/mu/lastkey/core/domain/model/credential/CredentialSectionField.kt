package com.mu.lastkey.core.domain.model.credential

import kotlinx.datetime.LocalDateTime

data class CredentialSectionField(
    val id: String,
    val sectionId: String,
    val label: String,
    val value: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
