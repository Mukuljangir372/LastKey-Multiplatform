package com.mu.lastkey.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mu.lastkey.core.ui.theme.LastKeyTheme

object Toolbars {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Primary(
        modifier: Modifier,
        title: @Composable () -> Unit,
        navigationIcon: @Composable () -> Unit = {},
        actions: @Composable RowScope.() -> Unit = {}
    ) {
        Surface(shadowElevation = 2.dp) {
            TopAppBar(
                modifier = modifier,
                title = title,
                navigationIcon = navigationIcon,
                actions = actions,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LastKeyTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f),
                    navigationIconContentColor = LastKeyTheme.colorScheme.primary,
                    titleContentColor = LastKeyTheme.colorScheme.primary,
                    actionIconContentColor = LastKeyTheme.colorScheme.primary
                )
            )
        }
    }
}
