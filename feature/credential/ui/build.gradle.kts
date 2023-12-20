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
                implementation(project(":common:ui"))
                implementation(project(":common:domain"))
                implementation(project(":feature:credential:domain"))
                implementation(libs.koin)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlinx.datetime)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.feature.credential.ui"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeKotlinCompilerExtension.get()
    }
}
