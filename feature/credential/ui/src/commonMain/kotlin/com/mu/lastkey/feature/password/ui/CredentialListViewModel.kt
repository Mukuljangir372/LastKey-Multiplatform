package com.mu.lastkey.feature.password.ui

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.core.domain.model.AppCoroutineDispatchers
import com.mu.lastkey.feature.password.domain.usecase.GetCredentialsUsecase
import com.mu.lastkey.feature.password.ui.model.CredentialModelMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Immutable
internal class CredentialListViewModel(
    private val dispatchers: AppCoroutineDispatchers,
    private val getCredentialsUsecase: GetCredentialsUsecase,
    private val credentialModelMapper: CredentialModelMapper
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
        loadCredentials(true)
    }

    private var loadCredentialsJob: Job? = null
    private fun loadCredentials(refresh: Boolean) {
        if (loadCredentialsJob?.isActive == true) return
        scope?.launch {
            val initialLoad = state.value.currentOffset == INITIAL_OFFSET
            state.update { it.copy(loading = initialLoad) }
            val newState = getCredentialsAsState(
                state = state,
                getCredentialsUsecase = getCredentialsUsecase,
                mapper = credentialModelMapper,
                refresh = refresh
            )
            state.update { newState }
        }
    }

    companion object {
        private const val PAGING_LIMIT = 40
        private const val INITIAL_OFFSET = 0
        private const val PAGING_KEY = "CREDENTIALS_LIST_PAGING"

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

        private suspend fun getCredentialsAsState(
            state: StateFlow<CredentialListState>,
            getCredentialsUsecase: GetCredentialsUsecase,
            mapper: CredentialModelMapper,
            refresh: Boolean
        ): CredentialListState {
            val previousOffset = state.value.currentOffset
            val nextOffset = if (refresh) INITIAL_OFFSET else previousOffset + PAGING_LIMIT
            val params = GetCredentialsUsecase.Params(
                offset = nextOffset, pagingKey = PAGING_KEY, refresh = refresh
            )
            val result = getCredentialsUsecase(params).data.orEmpty()
            val mappedResult = result.map { mapper.credentialToDisplayModel(it) }
            val credentials = if (refresh) mappedResult else state.value.credentials + mappedResult
            val currentOffset =
                if (isIncrementOffset(mappedResult.size)) nextOffset else previousOffset
            return state.value.copy(
                loading = false, currentOffset = currentOffset, credentials = credentials
            )
        }

        private fun isIncrementOffset(size: Int): Boolean {
            return PAGING_LIMIT >= size
        }
    }
}
