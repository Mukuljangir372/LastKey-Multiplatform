package com.mu.lastkey.feature.login.domain.repository

interface LoginRepository {
    suspend fun signIn()
}
