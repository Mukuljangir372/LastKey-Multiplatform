package com.mu.lastkey.dashboard

import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.mu.lastkey.core.ui.theme.LastKeyTheme

internal sealed interface DashboardNavScreen {
    data object Home : DashboardNavScreen
    data object Passwords : DashboardNavScreen
    data object Chats : DashboardNavScreen
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
            val passwords = DashboardBottomNavScreen(
                screen = Passwords,
                icon = LastKeyTheme.materialIcons.Default.Lock,
                label = LastKeyTheme.strings.passwords
            )
            val chats = DashboardBottomNavScreen(
                screen = Chats,
                icon = LastKeyTheme.materialIcons.Default.Email,
                label = LastKeyTheme.strings.chats
            )
            val search = DashboardBottomNavScreen(
                screen = Search,
                icon = LastKeyTheme.materialIcons.Default.Search,
                label = LastKeyTheme.strings.search
            )
            val more = DashboardBottomNavScreen(
                screen = More,
                icon = LastKeyTheme.materialIcons.Default.MoreVert,
                label = LastKeyTheme.strings.more
            )
            return listOf(home, passwords, chats, search, more)
        }
    }
}

internal data class DashboardBottomNavScreen(
    val screen: DashboardNavScreen,
    val icon: ImageVector,
    val label: String
)
