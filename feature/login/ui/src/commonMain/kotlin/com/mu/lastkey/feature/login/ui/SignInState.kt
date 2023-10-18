package com.mu.lastkey.feature.login.ui

import com.mu.lastkey.core.ui.model.MessageWrapper

internal data class SignInState(
    val email: String,
    val password: String,
    val signInLoading: Boolean,
    val signInSuccess: Boolean,
    val signUp: Boolean,
    val message: MessageWrapper
) {
    companion object {
        val idle = SignInState(
            email = "",
            password = "",
            signInLoading = false,
            signInSuccess = false,
            signUp = false,
            message = MessageWrapper.empty
        )
    }
}

sealed interface SignInUiState {
    object Idle : SignInUiState
    object SignUp : SignInUiState
    object Success : SignInUiState

    data class SignIn(
        val email: String,
        val password: String,
        val loading: Boolean,
        val message: MessageWrapper
    ) : SignInUiState
}
