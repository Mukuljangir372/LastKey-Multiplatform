package com.mu.lastkey.core.data.network

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CredentialNetworkModel(
    val id: String,
    val name: String,
    val sections: List<CredentialSectionNetworkModel>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
