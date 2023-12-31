@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
}

android {
    namespace = "com.kotlin.learn.core.utilities"
}

dependencies{
    implementation(libs.androidx.annotation.jvm)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.activity.activity.ktx)
    implementation(libs.androidx.activity.fragment.ktx)
    implementation(libs.timber)
}