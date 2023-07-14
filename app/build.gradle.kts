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
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:movie"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.paging.runtime)
    implementation(libs.bundles.ktor)
    implementation(libs.constraintlayout)
    implementation(libs.hilt.android)
    implementation(libs.timber)
    implementation(libs.androidx.navigation.fragment)

    kapt(libs.hilt.compiler)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)

}