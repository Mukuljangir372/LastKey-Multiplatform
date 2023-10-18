package com.mu.lastkey.feature.login.ui

import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.ui.model.MessageWrapper
import com.mu.lastkey.feature.login.domain.usecase.SignInUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
        signInUser()
    }

    fun signUp() {
        // TODO: Not yet implemented
    }

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun onMessageShown(id: String) {
        if (id != _state.value.message.id) return
        _state.update { it.copy(message = MessageWrapper.empty) }
    }

    private var signInJob: Job? = null
    private fun signInUser() {
        signInJob?.cancel()
        signInJob = scope?.launch {
            _state.update { it.copy(signInLoading = true) }
            val modifiedState = signInAsState(
                state = _state,
                signInUsecase = signInUsecase
            )
            _state.update { modifiedState.copy(signInLoading = false) }
        }
    }

    companion object {
        private fun mapStateToUiState(state: SignInState): SignInUiState {
            return if (state.signInSuccess) {
                SignInUiState.Success
            } else if (state.signUp) {
                SignInUiState.SignUp
            } else {
                SignInUiState.SignIn(
                    email = state.email,
                    password = state.password,
                    loading = state.signInLoading,
                    message = state.message
                )
            }
        }

        private suspend fun signIn(
            email: String,
            password: String,
            signInUsecase: SignInUsecase
        ): ResultWrapper<SignInUsecase.Result?> {
            val params = SignInUsecase.Params(email = email, password = password)
            return signInUsecase.invoke(params)
        }

        private suspend fun signInAsState(
            state: StateFlow<SignInState>,
            signInUsecase: SignInUsecase
        ): SignInState {
            val result = signIn(
                email = state.value.email,
                password = state.value.password,
                signInUsecase = signInUsecase
            )
            return state.value.copy(
                message = MessageWrapper.create(result.message),
                signInSuccess = result.isSuccess()
            )
        }
    }
}
