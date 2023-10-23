package com.mu.lastkey.core.ui

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.objc.KeyValueObserverProtocol
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.mu.lastkey.core.ui.theme.LastKeyWindow
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.useContents
import platform.Foundation.NSKeyValueObservingOptionNew
import platform.Foundation.addObserver
import platform.Foundation.removeObserver
import platform.UIKit.UIViewController
import platform.darwin.NSObject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalForeignApi::class)
@Composable
internal actual fun calculateLocalWindow(): LastKeyWindow {
    val uiViewController = LocalUIViewController.current
    val viewSize by remember(uiViewController) {
        mutableStateOf(uiViewController.getViewFrameSize())
    }

    var windowSizeClass by remember(uiViewController) {
        mutableStateOf(WindowSizeClass.calculateFromSize(viewSize, Density(1F, 1F)))
    }

    DisposableEffect(uiViewController) {
        val observer = ObserverObject {
            val size = uiViewController.getViewFrameSize()
            windowSizeClass = WindowSizeClass.calculateFromSize(size, Density(1F, 1F))
        }

        uiViewController.view.layer.addObserver(
            observer = observer,
            forKeyPath = "bounds",
            options = NSKeyValueObservingOptionNew,
            context = null
        )

        onDispose {
            uiViewController.view.layer.removeObserver(
                observer = observer,
                forKeyPath = "bounds"
            )
        }
    }

    return LastKeyWindow(
        width = viewSize.width.dp,
        height = viewSize.height.dp,
        widthSize = windowSizeClass.widthSizeClass,
        heightSize = windowSizeClass.heightSizeClass
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun UIViewController.getViewFrameSize(): Size = view.frame().useContents {
    // iOS returns density independent pixels, rather than raw pixels
    Size(size.width.toFloat(), size.height.toFloat())
}

@ExportObjCClass
private class ObserverObject(
    private val onChange: () -> Unit
) : NSObject(), KeyValueObserverProtocol {
    @ExperimentalForeignApi
    override fun observeValueForKeyPath(
        keyPath: String?,
        ofObject: Any?,
        change: Map<Any?, *>?,
        context: COpaquePointer?
    ) {
        onChange()
    }
}
