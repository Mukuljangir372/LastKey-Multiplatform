package com.mu.lastkey.android

import android.app.Application
import com.mu.lastkey.DependencyGraph

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyGraph(this).load()
    }
}
