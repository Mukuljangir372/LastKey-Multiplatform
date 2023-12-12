package com.mu.lastkey.feature.login.ui.signup

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.mu.lastkey.core.ui.model.MessageWrapper

@Stable
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

@Immutable
internal sealed interface SignUpUiState {
    data object Idle : SignUpUiState
    data object SignIn : SignUpUiState
    data object Success : SignUpUiState

    @Stable
    data class SignUp(
        val email: String,
        val password: String,
        val loading: Boolean,
        val message: MessageWrapper
    ) : SignUpUiState
}
