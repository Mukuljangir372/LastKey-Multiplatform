package com.mu.lastkey.build.logic.convention

import ANDROID_COMPILE_SDK_VERSION
import ANDROID_MIN_SDK_VERSION
import APPLICATION_ID
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

class KMMConventionPlugin : Plugin<Project> {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("org.jetbrains.kotlin.multiplatform")
            plugins.apply("com.android.library")

            extensions.getByType(KotlinMultiplatformExtension::class.java).apply {
                targetHierarchy.default()

                android {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = "1.8"
                            jvmToolchain(8)
                        }
                    }
                }

                iosX64()
                iosArm64()
                iosSimulatorArm64()

                // Need to add linker flag for SQLite
                // See: https://github.com/touchlab/SQLiter/issues/77
                targets
                    .filterIsInstance<KotlinNativeTarget>()
                    .flatMap { it.binaries }
                    .forEach { compilationUnit ->
                        compilationUnit.linkerOpts("-lsqlite3")
                    }
            }

            extensions.getByType(LibraryExtension::class.java).apply {
                compileSdk = ANDROID_COMPILE_SDK_VERSION
                defaultConfig {
                    minSdk = ANDROID_MIN_SDK_VERSION
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
            }
        }
    }
}