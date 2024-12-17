buildscript {
    dependencies {
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.google.services.gradlePlugin)
        classpath(libs.detekt.gradlePlugin)
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.kapt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.navigation.safeargs) apply false
}
true

allprojects {
    apply {
        from("$rootDir/buildConfig/local-aar-config.gradle")
        from("$rootDir/buildConfig/local-aar.gradle")
        plugin("io.gitlab.arturbosch.detekt")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
