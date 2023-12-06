package com.mu.lastkey.feature.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mu.lastkey.core.ui.navigation.AppNavigation
import com.mu.lastkey.core.ui.theme.LastKeyTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val viewModel: SplashViewModel by inject()
        val navigation: AppNavigation by inject()
        val navigator = LocalNavigator.currentOrThrow

        SplashUiScreen(
            viewModel = viewModel,
            toDashboard = {
                navigation.pop(navigator)
                navigation.dashboard(navigator)
            },
            toLogin = {
                navigation.pop(navigator)
                navigation.signIn(navigator)
            }
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SplashUiScreen(
    viewModel: SplashViewModel,
    toDashboard: () -> Unit,
    toLogin: () -> Unit
) {
    val state by viewModel.uiState.collectAsState(SplashUiState.Loading)

    LaunchedEffect(Unit) {
        viewModel.start()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                SplashUiState.Loading -> {
                    Image(
                        modifier = Modifier.height(70.dp),
                        painter = painterResource(LastKeyTheme.icons.AppIcon),
                        contentDescription = null
                    )
                }

                SplashUiState.Dashboard -> {
                    toDashboard()
                }

                SplashUiState.Login -> {
                    toLogin()
                }
            }
        }
    }
}
