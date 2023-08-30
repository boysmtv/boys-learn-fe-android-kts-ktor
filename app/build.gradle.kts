@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hilt)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:utilities"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:movie"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:services"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.paging.runtime)
    implementation(libs.bundles.ktor)
    implementation(libs.constraintlayout)
    implementation(libs.hilt.android)
    implementation(libs.timber)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.com.android.support.multidex)
    implementation(libs.androidx.datastore.datastore.preferences)
    implementation(libs.play.services.location)

    kapt(libs.hilt.compiler)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)

}