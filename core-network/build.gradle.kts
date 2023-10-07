plugins {
    id("com.mu.lastkey.kmm")
    alias(libs.plugins.realm)
}
kotlin {
    sourceSets {
        val androidMain by getting {
            dependencies {
                api(libs.ktor.client.okhttp)
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(project(":core-utils"))
                implementation(project(":core-logging"))
                api(libs.ktor.client.core)
                api(libs.ktor.client.content.negotation)
                api(libs.ktor.client.serialization)
                api(libs.ktor.logging)
                api(libs.realm)
                api(libs.realm.sync)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.koin)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.ktor.client.darwin)
            }
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.network"
}
