package com.mu.lastkey.feature.password.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.mu.lastkey.core.ui.components.Cards
import com.mu.lastkey.core.ui.components.Loaders
import com.mu.lastkey.core.ui.components.Toolbars
import com.mu.lastkey.core.ui.theme.LastKeyTheme
import com.mu.lastkey.feature.password.ui.model.CredentialDisplayModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CredentialListScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val viewModel: CredentialListViewModel by inject()
        CredentialListUiScreen(viewModel)
    }
}

@Composable
private fun CredentialListUiScreen(viewModel: CredentialListViewModel) {
    val state by viewModel.uiState.collectAsState(CredentialListUiState.Loading)

    LaunchedEffect(Unit) {
        viewModel.start()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbars.Primary(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = LastKeyTheme.strings.credentials,
                        style = LastKeyTheme.typo.titleMedium
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = rememberVectorPainter(LastKeyTheme.materialIcons.Default.MoreVert),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = {},
                icon = { Icon(LastKeyTheme.materialIcons.Default.Add, null) },
                text = { Text(LastKeyTheme.strings.addCredential) }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        when (state) {
            CredentialListUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(LastKeyTheme.colorScheme.surface)
                ) {
                    Loaders.Primary(modifier = Modifier.align(Alignment.Center))
                }
            }

            is CredentialListUiState.Credentials -> {
                Credentials(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(LastKeyTheme.colorScheme.surface),
                    state = state as CredentialListUiState.Credentials
                )
            }

            CredentialListUiState.NoResults -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(LastKeyTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(LastKeyTheme.strings.noResults)
                }
            }
        }
    }
}

@Composable
private fun Credentials(
    modifier: Modifier,
    state: CredentialListUiState.Credentials
) {
    LazyColumn(modifier) {
        items(
            count = state.list.size,
            key = { state.list[it].id }
        ) { index ->
            CredentialListItem(
                modifier = Modifier.fillMaxWidth(),
                model = state.list[index]
            )
        }
    }
}

@Composable
private fun CredentialListItem(
    modifier: Modifier,
    model: CredentialDisplayModel
) {
    Cards.Primary(
        modifier = modifier
            .wrapContentHeight()
            .padding(
                vertical = LastKeyTheme.dimens.two.dp,
                horizontal = LastKeyTheme.dimens.two.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LastKeyTheme.dimens.one.dp + LastKeyTheme.dimens.half.dp,
                    horizontal = LastKeyTheme.dimens.two.dp
                )
        ) {
            Text(
                text = model.name,
                style = LastKeyTheme.typo.titleMedium
            )
            Text(
                text = model.description,
                style = LastKeyTheme.typo.bodySmall
            )
        }
    }
}
