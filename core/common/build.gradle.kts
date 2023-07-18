@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
}

android {
    namespace = "com.kotlin.learn.core.common"
    viewBinding.isEnabled = true
}

dependencies {
    implementation(project(":core:utilities"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    implementation(libs.paging.runtime)
    implementation(libs.material)
    implementation(libs.androidx.security.security.crypto)
    implementation(libs.com.squareup.moshi.moshi.kotlin)
}
