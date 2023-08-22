@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
}

android {
    namespace = "com.kotlin.learn.core.network"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:utilities"))

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.bundles.ktor)

    implementation(libs.androidx.datastore.datastore.preferences)
    implementation(libs.com.google.android.gms.play.services.auth)
    implementation(libs.com.google.firebase.firebase.auth)
    implementation(libs.com.google.firebase.firebase.common.ktx)
    implementation(libs.com.google.firebase.firebase.database.ktx)
    implementation(libs.com.google.firebase.firebase.firestore.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)
}