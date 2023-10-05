package com.mu.lastkey.core.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object LastKeyTheme {
    val colors: LastKeyColors
        @Composable
        @ReadOnlyComposable
        get() = LastKeyColors

    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typo: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val spacing: LastKeySpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val dimens: LastKeyDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current

    val shapes: LastKeyShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val icons: LastKeyIcons
        @Composable
        @ReadOnlyComposable
        get() = LastKeyIcons

    val materialIcons: Icons
        @Composable
        @ReadOnlyComposable
        get() = Icons
}
