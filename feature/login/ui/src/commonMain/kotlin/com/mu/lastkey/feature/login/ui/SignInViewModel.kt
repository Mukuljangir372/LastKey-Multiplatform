package com.mu.lastkey.feature.login.ui

import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.core.domain.model.coroutine.AppCoroutineDispatchers
import com.mu.lastkey.feature.login.domain.usecase.SignInUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class SignInViewModel(
    private val signInUsecase: SignInUsecase,
    private val dispatchers: AppCoroutineDispatchers
) : ScreenModel {
    private var scope: CoroutineScope? = null

    private val _state = MutableStateFlow(SignInState.idle)
    val uiState: Flow<SignInUiState> get() = _state.map { mapStateToUiState(it) }

    private fun resetScope() {
        scope?.cancel()
        scope = null
        scope = CoroutineScope(dispatchers.main)
    }

    override fun onDispose() {
        super.onDispose()
        resetScope()
    }

    fun start() {
        resetScope()
    }

    fun signIn() {
        _state.update { it.copy(signInLoading = !it.signInLoading) }
    }

    fun signUp() {
        _state.update { it.copy(signUp = true) }
    }

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value) }
    }

    companion object {
        private fun mapStateToUiState(state: SignInState): SignInUiState {
            return if (state.signInSuccess) {
                SignInUiState.Success
            } else {
                SignInUiState.SignIn(
                    email = state.email,
                    password = state.password,
                    loading = state.signInLoading
                )
            }
        }

        private fun signIn() {
        }

        private fun signInAsState() {
        }
    }
}
