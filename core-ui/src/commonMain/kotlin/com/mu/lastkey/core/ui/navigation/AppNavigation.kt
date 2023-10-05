package com.mu.lastkey.core.ui.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

interface AppNavigation {
    fun initialScreen(): Screen
    fun pop(navigator: Navigator): AppNavigation
}
