package com.mu.lastkey.feature.login.ui

internal data class SignInState(
    val email: String,
    val password: String,
    val signInLoading: Boolean,
    val signInSuccess: Boolean,
    val signUp: Boolean
) {
    companion object {
        val idle = SignInState(
            email = "",
            password = "",
            signInLoading = false,
            signInSuccess = false,
            signUp = false
        )
    }
}

sealed interface SignInUiState {
    object Idle : SignInUiState
    data class SignIn(val email: String, val password: String, val loading: Boolean) : SignInUiState
    object SignUp : SignInUiState
    object Success : SignInUiState
}
