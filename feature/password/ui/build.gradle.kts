@file:Suppress("UnstableApiUsage")

plugins {
    id("com.mu.lastkey.kmm")
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.serialization)
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core-ui"))
                implementation(project(":core-domain"))
                implementation(project(":feature:password:domain"))
                implementation(libs.koin)
                implementation(libs.kotlin.coroutines.core)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.feature.password.ui"
    buildFeatures {
        compose = true
    }
}
