plugins {
    alias(libs.plugins.sports.android.library)
    alias(libs.plugins.sports.android.hilt)
}

android {
    namespace = "com.sports.datastore"
}

dependencies {
    implementation(libs.timber)
    api(libs.androidx.dataStore.core)
    api(libs.androidx.dataStore.preferences)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.protobuf.kotlin.lite)

    api(projects.core.datastoreProto)
    implementation(projects.core.common)

}