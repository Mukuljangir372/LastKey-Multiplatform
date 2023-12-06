import com.codingfeline.buildkonfig.compiler.FieldSpec
import java.util.Properties

plugins {
    id("com.mu.lastkey.kmm")
    alias(libs.plugins.realm)
    alias(libs.plugins.buildkonfig)
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
buildkonfig {
    packageName = "com.mu.lastkey.core.network"
    objectName = "BuildConfig"
    exposeObjectWithName = "BuildConfig"

    val props = Properties()
    props.load(project.rootProject.file("local.properties").inputStream())
    val realmAppID = props.getProperty("REALM_APP_ID").toString()
    val realmApiKey = props.getProperty("REALM_API_KEY").toString()

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "REALM_APP_ID", realmAppID)
        buildConfigField(FieldSpec.Type.STRING, "REALM_API_KEY", realmApiKey)
    }
}
