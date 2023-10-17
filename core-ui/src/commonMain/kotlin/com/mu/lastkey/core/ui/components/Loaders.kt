package com.mu.lastkey.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mu.lastkey.core.ui.theme.LastKeyTheme

object Loaders {
    @Composable
    fun Primary(
        modifier: Modifier
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(LastKeyTheme.dimens.three.dp),
            color = LastKeyTheme.colorScheme.onPrimary,
            strokeWidth = 3.dp
        )
    }
}
