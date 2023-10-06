package com.mu.lastkey.feature.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class SignInScreen: Screen {
    @Composable
    override fun Content() {
        SignInUiScreen()
    }
}

@Composable
private fun SignInUiScreen() {
    SignInUiScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SignInUiScreenContent() {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Logo()
        }
    }
}

@Composable
private fun Logo() {
    Text("LastKey")
}
















