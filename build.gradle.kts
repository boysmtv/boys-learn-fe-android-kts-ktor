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
    alias(libs.plugins.sonarqube)
}
true

allprojects {
    apply {
        from("$rootDir/buildConfig/local-aar-config.gradle")
        from("$rootDir/buildConfig/local-aar.gradle")
        plugin("io.gitlab.arturbosch.detekt")
    }

    apply {
        sonarqube {
            properties {
                property("sonar.projectKey", "boys-learn-fe-android-kts-ktor")
                property("sonar.organization", "boysmtv")
                property("sonar.host.url", "https://sonarcloud.io")
                property("sonar.sources", "src")
                property("sonar.language", "kotlin")
                property("sonar.sourceEncoding", "UTF-8")
            }
        }

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
