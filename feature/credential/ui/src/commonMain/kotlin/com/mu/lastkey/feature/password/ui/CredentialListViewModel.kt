package com.mu.lastkey.feature.password.ui

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

@Immutable
internal class CredentialListViewModel(
    private val dispatchers: AppCoroutineDispatchers
) : ScreenModel {
    private var scope: CoroutineScope? = null

    private val state = MutableStateFlow(CredentialListState.idle)
    val uiState: Flow<CredentialListUiState> get() = state.map { mapStateToUiState(it) }

    private fun resetScope() {
        scope?.cancel()
        scope = CoroutineScope(dispatchers.default)
    }

    override fun onDispose() {
        super.onDispose()
        resetScope()
    }

    fun start() {
        resetScope()
    }

    companion object {
        private fun mapStateToUiState(
            state: CredentialListState
        ): CredentialListUiState {
            return if (state.loading) {
                CredentialListUiState.Loading
            } else if (state.credentials.isNotEmpty()) {
                CredentialListUiState.Credentials(list = state.credentials)
            } else {
                CredentialListUiState.NoResults
            }
        }
    }
}
