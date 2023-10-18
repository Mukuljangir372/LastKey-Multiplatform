package com.mu.lastkey.feature.login.ui.signup

import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.core.domain.model.ResultWrapper
import com.mu.lastkey.core.ui.model.MessageWrapper
import com.mu.lastkey.feature.login.domain.usecase.SignUpUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUsecase: SignUpUsecase,
    private val dispatchers: AppCoroutineDispatchers
) : ScreenModel {
    private var scope: CoroutineScope? = null

    private val _state = MutableStateFlow(SignUpState.idle)
    val uiState: Flow<SignUpUiState> get() = _state.map { mapStateToUiState(it) }

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

    fun signUp() {
        signUpUser()
    }

    fun signIn() {
        _state.update { it.copy(signIn = true) }
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

    fun onSignIn() {
        _state.update { it.copy(signIn = false) }
    }

    private var signUpJob: Job? = null
    private fun signUpUser() {
        signUpJob?.cancel()
        signUpJob = scope?.launch {
            _state.update { it.copy(signUpLoading = true) }
            val modifiedState = signUpAsState(
                state = _state,
                signUpUsecase = signUpUsecase
            )
            _state.update { modifiedState.copy(signUpLoading = false) }
        }
    }

    companion object {
        private fun mapStateToUiState(state: SignUpState): SignUpUiState {
            return if (state.signUpSuccess) {
                SignUpUiState.Success
            } else if (state.signIn) {
                SignUpUiState.SignIn
            } else {
                SignUpUiState.SignUp(
                    email = state.email,
                    password = state.password,
                    loading = state.signUpLoading,
                    message = state.message
                )
            }
        }

        private suspend fun signUp(
            email: String,
            password: String,
            signUpUsecase: SignUpUsecase
        ): ResultWrapper<SignUpUsecase.Result?> {
            val params = SignUpUsecase.Params(email = email, password = password)
            return signUpUsecase.invoke(params)
        }

        private suspend fun signUpAsState(
            state: StateFlow<SignUpState>,
            signUpUsecase: SignUpUsecase
        ): SignUpState {
            val result = signUp(
                email = state.value.email,
                password = state.value.password,
                signUpUsecase = signUpUsecase
            )
            return state.value.copy(
                message = MessageWrapper.create(result.message),
                signUpSuccess = result.isSuccess()
            )
        }
    }
}
