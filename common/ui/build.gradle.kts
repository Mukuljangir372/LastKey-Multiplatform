@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.mu.lastkey.kmm")
    alias(libs.plugins.compose)
}
kotlin {
    sourceSets {
        val androidMain by getting {
            dependencies {
                api(compose.uiTooling)
                api(compose.preview)
                api(libs.androidx.window)
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(project(":core-utils"))
                implementation(project(":common:domain"))
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.material3)
                api(libs.compose.material3.windowsize)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.components.resources)
                api(libs.bundles.voyager)
                implementation(libs.kotlin.coroutines.core)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}
android {
    namespace = "com.mu.lastkey.common.ui"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeKotlinCompilerExtension.get()
    }
}
