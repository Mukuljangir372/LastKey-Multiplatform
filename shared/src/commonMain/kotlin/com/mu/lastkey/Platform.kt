package com.mu.lastkey

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
