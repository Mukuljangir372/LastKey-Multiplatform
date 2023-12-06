package com.mu.lastkey.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mu.lastkey.core.ui.theme.LastKeyTheme

object Cards {
    @Composable
    fun Basic(
        modifier: Modifier,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Card(
            modifier = modifier,
            content = content,
            colors = CardDefaults.outlinedCardColors(
                containerColor = LastKeyTheme.colorScheme.background,
                contentColor = LastKeyTheme.colorScheme.onBackground
            ),
            border = BorderStroke(
                width = 1.dp,
                color = LastKeyTheme.colorScheme.outline
            )
        )
    }
}
