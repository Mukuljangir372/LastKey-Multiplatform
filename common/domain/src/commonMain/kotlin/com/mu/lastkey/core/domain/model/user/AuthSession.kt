package com.mu.lastkey.core.domain.model.user

data class AuthSession(
    val authUserId: String,
    val active: Boolean
)
