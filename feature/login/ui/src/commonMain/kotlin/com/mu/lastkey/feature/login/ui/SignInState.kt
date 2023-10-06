package com.mu.lastkey.feature.login.ui

internal data class SignInState(
    val username: String,
    val password: String,
    val signInLoading: Boolean
)

internal sealed interface SignInUiState {
    object Idle : SignInUiState
    object Loading : SignInUiState
    data class SignIn(val username: String, val password: String) : SignInUiState
}