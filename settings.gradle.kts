pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
rootProject.name = "LastKey"

includeBuild("build-logic")
include(
    ":app-android",
    ":shared",
    ":core-network",
    ":core-database",
    ":core-preferences",
    ":core-logging",
    ":core-ui",
    ":core-data",
    ":core-domain",
    ":core-utils",
    ":feature",
    ":feature:login",
    ":feature:login:data",
    ":feature:login:domain",
    ":feature:login:ui",
    ":feature:password",
    ":feature:password:data",
    ":feature:password:domain",
    ":feature:password:ui",
)
