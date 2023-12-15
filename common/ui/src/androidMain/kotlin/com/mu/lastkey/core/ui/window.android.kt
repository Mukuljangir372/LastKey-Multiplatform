package com.mu.lastkey.core.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator
import com.mu.lastkey.core.ui.theme.LastKeyWindow

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
internal actual fun calculateLocalWindow(): LastKeyWindow {
    val activity = LocalContext.current.findActivity()
    val density = LocalDensity.current
    val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
    val size = metrics.bounds.toComposeRect().size
    val windowSizeClass = WindowSizeClass.calculateFromSize(size, density)
    return LastKeyWindow(
        width = size.width.dp,
        height = size.height.dp,
        widthSize = windowSizeClass.widthSizeClass,
        heightSize = windowSizeClass.heightSizeClass
    )
}

private tailrec fun Context.findActivity(): Activity = when (this) {
    is Activity -> this
    is ContextWrapper -> this.baseContext.findActivity()
    else -> error("Could not find activity in Context chain.")
}
