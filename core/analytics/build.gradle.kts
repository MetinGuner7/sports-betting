plugins {
    alias(libs.plugins.sports.android.library)
    alias(libs.plugins.sports.android.library.compose)
    alias(libs.plugins.sports.android.hilt)
}

android {
    namespace = "com.sports.analytics"
}

dependencies {
    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
    implementation(libs.compose.runtime)
    implementation(libs.timber)
}
