package com.mu.lastkey

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.mu.lastkey.core.ui.theme.LastKeyUiTheme
import com.mu.lastkey.feature.login.ui.signin.SignInScreen

@Composable
internal fun App() {
    LastKeyUiTheme {
        Navigator(SignInScreen())
    }
}
