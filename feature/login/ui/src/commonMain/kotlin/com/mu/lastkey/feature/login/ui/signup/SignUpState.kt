package com.mu.lastkey.feature.login.ui.signup

import com.mu.lastkey.core.ui.model.MessageWrapper

internal data class SignUpState(
    val email: String,
    val password: String,
    val signUpLoading: Boolean,
    val signUpSuccess: Boolean,
    val signIn: Boolean,
    val message: MessageWrapper
) {
    companion object {
        val idle = SignUpState(
            email = "",
            password = "",
            signUpLoading = false,
            signUpSuccess = false,
            signIn = false,
            message = MessageWrapper.empty
        )
    }
}

sealed interface SignUpUiState {
    object Idle : SignUpUiState
    object SignIn : SignUpUiState
    object Success : SignUpUiState

    data class SignUp(
        val email: String,
        val password: String,
        val loading: Boolean,
        val message: MessageWrapper
    ) : SignUpUiState
}
