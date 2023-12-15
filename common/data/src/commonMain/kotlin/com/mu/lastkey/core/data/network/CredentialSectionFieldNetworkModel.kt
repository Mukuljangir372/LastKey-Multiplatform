package com.mu.lastkey.core.data.network

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CredentialSectionFieldNetworkModel(
    val id: String,
    val sectionId: String,
    val label: String,
    val value: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
