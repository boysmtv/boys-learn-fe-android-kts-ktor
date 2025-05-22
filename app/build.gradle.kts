@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hilt)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
    from("$rootDir/gradle/install-git-hooks.gradle")
}

android {
    namespace = "com.kotlin.learn"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.kotlin.learn"
        minSdk = 24
        compileSdk = 33

        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }

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

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:utilities"))
    implementation(project(":feature:menu"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:movie"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:services"))

    implementation(libs.bundles.ktor)
    implementation(libs.bundles.play.services)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.datastore.datastore.preferences)
    implementation(libs.material)
    implementation(libs.paging.runtime)
    implementation(libs.constraintlayout)
    implementation(libs.hilt.android)
    implementation(libs.timber)
    implementation(libs.com.android.support.multidex)
    implementation(libs.com.jakewharton.threetenabp.threetenabp)

    kapt(libs.hilt.compiler)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)
}