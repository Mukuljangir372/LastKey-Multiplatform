package com.mu.lastkey

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun AppIOSView(): UIViewController {
    return ComposeUIViewController { App() }
}