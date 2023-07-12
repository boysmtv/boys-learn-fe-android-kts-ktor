@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hilt)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
}
apply(plugin = "org.jetbrains.kotlin.android")

android {
    namespace = "com.kotlin.learn.catalog.feature.auth"
    viewBinding.isEnabled = true
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:utilities"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:nav"))
    implementation(project(":core:ui"))

    implementation(libs.androidx.activity.activity.ktx)
    implementation(libs.androidx.activity.fragment.ktx)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.android)

    implementation(libs.bundles.ktor)
    implementation(libs.bundles.viewmodel)
    implementation(libs.paging.runtime)
    implementation(libs.facebook.shimmer)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.androidx.navigation.fragment)

    kapt(libs.hilt.compiler)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)
}