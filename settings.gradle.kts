pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven( url = uri("https://www.jitpack.io"))
    }
}

rootProject.name = "LearnKtorHiltCatalogPagging"
include(":app")
include(":core:common")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:network")
include(":core:utilities")
include(":core:nav")
include(":core:ui")
include(":feature:movie")
include(":feature:splashscreen")
