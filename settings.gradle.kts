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
    ":common:data",
    ":common:domain",
    ":common:ui",
    ":core-network",
    ":core-database",
    ":core-preferences",
    ":core-logging",
    ":core-utils",
    ":feature",
    ":feature:login",
    ":feature:login:data",
    ":feature:login:domain",
    ":feature:login:ui",
    ":feature:credential:data",
    ":feature:credential:domain",
    ":feature:credential:ui",
    ":feature:splash",
    ":feature:splash:ui",
    ":feature:home",
    ":feature:home:data",
    ":feature:home:domain",
    ":feature:home:ui"
)
