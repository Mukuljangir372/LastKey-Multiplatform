package com.mu.lastkey.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mu.lastkey.core.ui.theme.LastKeyTheme

object Buttons {
    @Composable
    fun Primary(
        modifier: Modifier,
        defaultHeight: Boolean = true,
        onClick: () -> Unit,
        content: @Composable RowScope.() -> Unit
    ) {
        Button(
            modifier = modifier.then(
                if (defaultHeight) {
                    Modifier.size(45.dp)
                } else {
                    Modifier
                }
            ),
            onClick = onClick,
            content = content,
            shape = LastKeyTheme.shapes.roundRectangleSmall
        )
    }

    @Composable
    fun PrimaryOutlined(
        modifier: Modifier,
        defaultHeight: Boolean = true,
        onClick: () -> Unit,
        content: @Composable RowScope.() -> Unit
    ) {
        OutlinedButton(
            modifier = modifier.then(
                if (defaultHeight) {
                    Modifier.size(45.dp)
                } else {
                    Modifier
                }
            ),
            onClick = onClick,
            content = content,
            shape = LastKeyTheme.shapes.roundRectangleSmall
        )
    }
}
