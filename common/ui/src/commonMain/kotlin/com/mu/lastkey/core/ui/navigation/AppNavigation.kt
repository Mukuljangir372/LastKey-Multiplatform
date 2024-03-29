package com.mu.lastkey.core.ui.navigation

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

@Immutable
interface AppNavigation {
    fun initialScreen(): Screen
    fun pop(navigator: Navigator): AppNavigation
    fun signIn(navigator: Navigator): AppNavigation
    fun signUp(navigator: Navigator): AppNavigation
    fun dashboard(navigator: Navigator): AppNavigation
    fun home(navigator: Navigator): AppNavigation
}
