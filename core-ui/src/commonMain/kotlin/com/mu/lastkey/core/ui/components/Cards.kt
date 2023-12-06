package com.mu.lastkey.core.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mu.lastkey.core.ui.theme.LastKeyTheme

object Cards {
    @Composable
    fun Primary(
        modifier: Modifier,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Card(
            modifier = modifier,
            content = content,
            colors = CardDefaults.elevatedCardColors(
                containerColor = LastKeyTheme.colorScheme.background,
                contentColor = LastKeyTheme.colorScheme.onBackground
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = LastKeyTheme.dimens.quarter.dp
            )
        )
    }
}
