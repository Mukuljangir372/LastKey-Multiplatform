package com.mu.lastkey.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DashboardScreen : Screen, KoinComponent {
    private val viewModel: DashboardViewModel by inject()

    @Composable
    override fun Content() {
        DashboardUiScreen(viewModel)
    }
}

@Composable
private fun DashboardUiScreen(viewModel: DashboardViewModel) {
    val state: DashboardUiState by viewModel.state.collectAsState(DashboardUiState.Idle)
    val stateHolder by remember { mutableStateOf(DashboardStateHolder()) }
    val selectedScreen by stateHolder.selectedScreen.collectAsState()
    val navItems = stateHolder.getBottomNavItems()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                navItems.forEach { item ->
                    val selected = selectedScreen == item.screen
                    NavigationBarItem(
                        selected = selected,
                        icon = {
                            Icon(
                                painter = rememberVectorPainter(item.icon),
                                contentDescription = null
                            )
                        },
                        onClick = {
                            stateHolder.selectScreen(item.screen)
                        }
                    )
                }
            }
        }
    ) { _ ->
        when (selectedScreen) {
            is DashboardNavScreen.Home -> {
                Text("Home")
            }
            is DashboardNavScreen.Passwords -> {
                Text("Passwords")
            }
            is DashboardNavScreen.Chats -> {
                Text("Chats")
            }
            is DashboardNavScreen.Search -> {
                Text("Search")
            }
            is DashboardNavScreen.More -> {
                Text("More")
            }
        }
    }
}
