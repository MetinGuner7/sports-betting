plugins {
    alias(libs.plugins.sports.android.library)
    alias(libs.plugins.sports.android.hilt)

}

android {
    namespace = "com.sports.monitor"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)
    implementation(projects.core.common)
}
