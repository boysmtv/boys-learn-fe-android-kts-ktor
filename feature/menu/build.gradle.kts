@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hilt)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
}

android {
    namespace = "com.kotlin.learn.feature.menu"
    viewBinding.isEnabled = true
}

val customModulePath: groovy.lang.Closure<Any> by ext

dependencies {
    implementation(customModulePath(":core:common"))
    implementation(customModulePath(":core:model"))
    implementation(customModulePath(":core:utilities"))
    implementation(customModulePath(":core:data"))
    implementation(customModulePath(":core:domain"))
    implementation(customModulePath(":core:nav"))
    implementation(customModulePath(":core:ui"))
    implementation(customModulePath(":feature:movie"))
    implementation(customModulePath(":feature:common"))

    implementation(libs.hilt.android)
    implementation(libs.constraintlayout)
    implementation(libs.kotlinx.datetime)
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.activity.ktx)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    kapt(libs.hilt.compiler)
}