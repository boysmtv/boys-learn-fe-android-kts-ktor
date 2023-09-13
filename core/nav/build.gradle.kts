@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.navigation.safeargs)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
}

android {
    namespace = "com.kotlin.learn.core.nav"
    viewBinding.isEnabled = true
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:utilities"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    implementation(libs.constraintlayout)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.android)

    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.navigation.fragment)

    kapt(libs.hilt.compiler)
}