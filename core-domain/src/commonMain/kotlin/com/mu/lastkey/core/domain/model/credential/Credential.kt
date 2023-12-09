package com.mu.lastkey.core.domain.model.credential

import kotlinx.datetime.LocalDateTime

data class Credential(
    val id: String,
    val displayName: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
