package com.mu.lastkey.core.data.network

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CredentialSectionNetworkModel(
    val id: String,
    val name: String,
    val credentialId: String,
    val fields: List<CredentialSectionFieldNetworkModel>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)