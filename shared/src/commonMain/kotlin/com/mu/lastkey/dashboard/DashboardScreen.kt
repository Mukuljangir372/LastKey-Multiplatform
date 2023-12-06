package com.mu.lastkey.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.mu.lastkey.core.ui.theme.LastKeyTheme
import com.mu.lastkey.home.HomeScreen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class DashboardScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val viewModel: DashboardViewModel by inject()
        DashboardUiScreen(viewModel)
    }
}

@Composable
private fun DashboardUiScreen(viewModel: DashboardViewModel) {
    val state: DashboardUiState by viewModel.state.collectAsState(DashboardUiState.Idle)
    val stateHolder by remember { mutableStateOf(DashboardStateHolder()) }
    val selectedScreen by stateHolder.selectedScreen.collectAsState()
    val navItems = stateHolder.getBottomNavItems()

    TabNavigator(HomeTab) {
        val tabNavigator = LocalTabNavigator.current
        when (selectedScreen) {
            DashboardNavScreen.Home -> {
                tabNavigator.current = HomeTab
            }
            DashboardNavScreen.Passwords -> {
                tabNavigator.current = PasswordsTab
            }
            DashboardNavScreen.Search -> {
                tabNavigator.current = SearchTab
            }
            DashboardNavScreen.More -> {
                tabNavigator.current = MoreTab
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = LastKeyTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
                ) {
                    navItems.forEach { item ->
                        val selected = selectedScreen == item.screen
                        NavigationBarItem(
                            selected = selected,
                            onClick = { stateHolder.selectScreen(item.screen) },
                            icon = {
                                Icon(
                                    painter = rememberVectorPainter(item.icon),
                                    contentDescription = null
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = LastKeyTheme.colorScheme.primary
                            )
                        )
                    }
                }
            },
            content = { _ ->
                CurrentTab()
            }
        )
    }
}

private object HomeTab : Tab {
    override val options: TabOptions
        @Composable get() = remember { TabOptions(index = 0u, title = "") }

    @Composable
    override fun Content() {
        HomeScreen().Content()
    }
}

private object PasswordsTab : Tab {
    override val options: TabOptions
        @Composable get() = remember { TabOptions(index = 0u, title = "") }

    @Composable
    override fun Content() {
        Text("Passwords")
    }
}

private object SearchTab : Tab {
    override val options: TabOptions
        @Composable get() = remember { TabOptions(index = 0u, title = "") }

    @Composable
    override fun Content() {
        Text("Search")
    }
}

private object MoreTab : Tab {
    override val options: TabOptions
        @Composable get() = remember { TabOptions(index = 0u, title = "") }

    @Composable
    override fun Content() {
        Text("More")
    }
}
