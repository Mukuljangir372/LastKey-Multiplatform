package com.mu.lastkey.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mu.lastkey.core.ui.theme.LastKeyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTextField(
    value: String,
    placeholder: @Composable () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.border(
            width = 0.5.dp,
            color = LastKeyTheme.colorScheme.outline.copy(alpha = 0.3f),
            shape = LastKeyTheme.shapes.roundRectangleSmall
        ),
        value = value,
        placeholder = placeholder,
        onValueChange = {},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Unspecified,
            unfocusedBorderColor = Color.Unspecified
        )
    )
}
