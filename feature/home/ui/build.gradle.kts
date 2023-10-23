@file:Suppress("UnstableApiUsage")

plugins {
    id("com.mu.lastkey.kmm")
    alias(libs.plugins.compose)
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core-ui"))
                implementation(project(":core-domain"))
                implementation(project(":feature:home:domain"))
                implementation(libs.koin)
                implementation(libs.kotlin.coroutines.core)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.feature.home.ui"
    buildFeatures {
        compose = true
    }
}
