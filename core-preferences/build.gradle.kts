plugins {
    id("com.mu.lastkey.kmm")
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.androidx.datastore.core)
                api(libs.androidx.datastore.okio)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.koin)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.koin.android)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
        }
    }
}
android {
    namespace = "com.mu.lastkey.core.preferences"
}
