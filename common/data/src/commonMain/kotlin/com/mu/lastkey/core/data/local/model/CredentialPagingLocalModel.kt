package com.mu.lastkey.core.data.local.model

data class CredentialPagingLocalModel(
    val id: String,
    val pagingKey: String,
    val offset: Int,
    val offsetIds: List<String>
)
