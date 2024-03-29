package com.mu.lastkey.dashboard

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.mu.lastkey.core.ui.theme.LastKeyTheme

internal sealed interface DashboardNavScreen {
    data object Home : DashboardNavScreen
    data object Credentials : DashboardNavScreen
    data object Search : DashboardNavScreen
    data object More : DashboardNavScreen

    companion object {
        @Composable
        fun getScreens(): List<DashboardBottomNavScreen> {
            val home = DashboardBottomNavScreen(
                screen = Home,
                icon = LastKeyTheme.materialIcons.Default.Home,
                label = LastKeyTheme.strings.home
            )
            val credentials = DashboardBottomNavScreen(
                screen = Credentials,
                icon = LastKeyTheme.materialIcons.Default.Menu,
                label = LastKeyTheme.strings.credentials
            )
            val search = DashboardBottomNavScreen(
                screen = Search,
                icon = LastKeyTheme.materialIcons.Default.Search,
                label = LastKeyTheme.strings.search
            )
            val more = DashboardBottomNavScreen(
                screen = More,
                icon = LastKeyTheme.materialIcons.Default.Settings,
                label = LastKeyTheme.strings.more
            )
            return listOf(home, credentials, search, more)
        }
    }
}

internal data class DashboardBottomNavScreen(
    val screen: DashboardNavScreen,
    val icon: ImageVector,
    val label: String
)
