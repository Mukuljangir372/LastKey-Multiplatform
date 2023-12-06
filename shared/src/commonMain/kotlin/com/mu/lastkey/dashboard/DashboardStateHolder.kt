package com.mu.lastkey.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// NOTE: collectAsState or get() mark as @Composable crash on ios
@Stable
internal class DashboardStateHolder {
    private val _selectedScreen = MutableStateFlow<DashboardNavScreen>(DashboardNavScreen.Home)
    val selectedScreen: StateFlow<DashboardNavScreen> get() = _selectedScreen

    fun selectScreen(screen: DashboardNavScreen) {
        _selectedScreen.value = screen
    }

    @Composable
    fun getBottomNavItems(): List<DashboardBottomNavScreen> {
        return DashboardNavScreen.getScreens()
    }
}
