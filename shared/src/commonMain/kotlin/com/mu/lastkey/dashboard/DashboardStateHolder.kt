package com.mu.lastkey.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow

@Stable
class DashboardStateHolder {
    private val _selectedScreen = MutableStateFlow<DashboardNavScreen>(DashboardNavScreen.Home)
    val selectedScreen: State<DashboardNavScreen>
        @Composable get() = _selectedScreen.collectAsState()

    fun selectScreen(screen: DashboardNavScreen) {
        _selectedScreen.value = screen
    }

    @Composable
    fun getBottomNavItems(): List<DashboardBottomNavScreen> {
        return DashboardNavScreen.getScreens()
    }
}
