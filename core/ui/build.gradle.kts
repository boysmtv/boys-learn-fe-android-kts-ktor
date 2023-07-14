@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
}

android {
    namespace = "com.kotlin.learn.core.ui"
    viewBinding.isEnabled = true
}

dependencies {
    implementation(libs.constraintlayout)
    implementation(libs.kotlinx.datetime)
    implementation(libs.material)
}