package com.mu.lastkey.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.mu.lastkey.core.ui.components.Loaders
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class HomeScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel by inject()
        HomeUiScreen(viewModel)
    }
}

@Composable
private fun HomeUiScreen(viewModel: HomeViewModel) {
    val state by viewModel.uiState.collectAsState(HomeUiState.Idle)

    LaunchedEffect(Unit) {
        viewModel.start()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        when (state) {
            HomeUiState.Loading -> {
                Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                    Loaders.Primary(modifier = Modifier.align(Alignment.Center))
                }
            }

            HomeUiState.Home -> {
                Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                    Text("Welcome")
                }
            }
            else -> {}
        }
    }
}
