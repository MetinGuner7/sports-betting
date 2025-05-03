plugins {
    alias(libs.plugins.sports.android.library)
    alias(libs.plugins.sports.android.library.compose)
    alias(libs.plugins.sports.android.hilt)
    alias(libs.plugins.serialization)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.sports.common"
}

dependencies {
    api(libs.androidx.lifecycle.runtime.compose)
    api(libs.kotlinx.serialization.json)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit.converter.moshi)
}