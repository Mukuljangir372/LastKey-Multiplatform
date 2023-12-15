package com.mu.lastkey.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Stable
data class LastKeyShapes(
    val roundRectangle: Shape,
    val roundRectangleSmall: Shape,
    val roundRectangleMedium: Shape,
    val roundRectangleLarge: Shape,
    val roundTopRectangleLarge: Shape
) {
    companion object {
        val compat = LastKeyShapes(
            roundRectangle = RoundedCornerShape(12.dp),
            roundRectangleSmall = RoundedCornerShape(8.dp),
            roundRectangleMedium = RoundedCornerShape(18.dp),
            roundRectangleLarge = RoundedCornerShape(24.dp),
            roundTopRectangleLarge = RoundedCornerShape(24.dp, 24.dp)
        )
    }
}
