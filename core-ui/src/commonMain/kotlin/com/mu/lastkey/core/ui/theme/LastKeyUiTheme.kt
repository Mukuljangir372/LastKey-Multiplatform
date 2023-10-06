package com.mu.lastkey.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.mu.lastkey.core.ui.calculateLocalWindow

// Guidelines - https://m3.material.io/styles/color/the-color-system/color-roles
// Figma - https://www.figma.com/community/file/1248805263844976008/Build-a-Material-color-scheme

private fun getLightColorScheme(): ColorScheme {
    return lightColorScheme(
        primary = LastKeyColors.Blue400,
        onPrimary = LastKeyColors.White1000,
        primaryContainer = LastKeyColors.Blue100,
        onPrimaryContainer = LastKeyColors.Blue500,
        secondary = LastKeyColors.Orange1000,
        onSecondary = LastKeyColors.White1000,
        secondaryContainer = LastKeyColors.Orange500,
        onSecondaryContainer = LastKeyColors.Orange1100,
        tertiary = LastKeyColors.Blue100,
        onTertiary = LastKeyColors.White1000,
        tertiaryContainer = LastKeyColors.Blue200,
        onTertiaryContainer = LastKeyColors.Blue500,
        error = LastKeyColors.Red1000,
        onError = LastKeyColors.White1000,
        errorContainer = LastKeyColors.Red500,
        onErrorContainer = LastKeyColors.Red1100,
        surface = LastKeyColors.White1000,
        onSurface = LastKeyColors.Black1000,
        background = LastKeyColors.White1000,
        onBackground = LastKeyColors.Black1000
    )
}

private fun getDarkColorScheme(): ColorScheme {
    return darkColorScheme(
        primary = LastKeyColors.Blue400,
        onPrimary = LastKeyColors.White1000,
        primaryContainer = LastKeyColors.Blue100,
        onPrimaryContainer = LastKeyColors.Blue500,
        secondary = LastKeyColors.Orange1000,
        onSecondary = LastKeyColors.Black1000,
        secondaryContainer = LastKeyColors.Orange500,
        onSecondaryContainer = LastKeyColors.Orange1100,
        tertiary = LastKeyColors.Blue100,
        onTertiary = LastKeyColors.Black1000,
        tertiaryContainer = LastKeyColors.Blue200,
        onTertiaryContainer = LastKeyColors.Blue500,
        error = LastKeyColors.Red1000,
        onError = LastKeyColors.Black1000,
        errorContainer = LastKeyColors.Red500,
        onErrorContainer = LastKeyColors.Red1100,
        surface = LastKeyColors.Black1000,
        onSurface = LastKeyColors.Black1000,
        background = LastKeyColors.Black1000,
        onBackground = LastKeyColors.Black1000
    )
}

internal val LocalDimens = staticCompositionLocalOf { LastKeyDimens() }
internal val LocalSpacing = staticCompositionLocalOf { LastKeySpacing() }
internal val LocalWindow = staticCompositionLocalOf { LastKeyWindow.default }
internal val LocalShapes = staticCompositionLocalOf { LastKeyShapes.compat }
internal val LocalColors = staticCompositionLocalOf { getLightColorScheme() }
internal val LocalTypo = staticCompositionLocalOf { Typography() }

@Composable
fun LastKeyUiTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val windowSize = calculateLocalWindow()

    val colors = if (isDarkMode) {
        getDarkColorScheme()
    } else {
        getLightColorScheme()
    }

    val shapes = LastKeyShapes.compat
    val typography = MaterialTheme.typography

    MaterialTheme(
        colorScheme = colors,
        typography = typography
    ) {
        CompositionLocalProvider(
            LocalWindow provides windowSize,
            LocalColors provides colors,
            LocalTypo provides typography,
            LocalShapes provides shapes
        ) {
            content()
        }
    }
}
