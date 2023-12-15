package com.mu.lastkey.core.ui.theme

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
class LastKeyWindow(
    val width: Dp,
    val height: Dp,
    val widthSize: WindowWidthSizeClass,
    val heightSize: WindowHeightSizeClass
) {
    companion object {
        private const val compatWidth = 600f
        private const val compatHeight = 900f
        val default = LastKeyWindow(
            width = compatWidth.dp,
            height = compatHeight.dp,
            widthSize = WindowWidthSizeClass.Compact,
            heightSize = WindowHeightSizeClass.Compact
        )
    }
}
