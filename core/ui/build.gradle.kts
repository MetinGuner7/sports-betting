plugins {
    alias(libs.plugins.sports.android.library)
    alias(libs.plugins.sports.android.library.compose)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.sports.ui"
}

dependencies {
    api(projects.core.analytics)

    implementation(projects.core.designsystem)

    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.common.ktx)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.lottie.compose)
    implementation(libs.firebase.analytics)
}
