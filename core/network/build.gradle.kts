plugins {
    alias(libs.plugins.sports.android.library)
    alias(libs.plugins.sports.android.hilt)
    id(libs.plugins.secrets.get().pluginId)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.sports.network"
    buildFeatures {
        buildConfig = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.monitor)
    implementation(projects.core.datastore)

    implementation(libs.kotlinx.coroutines.android)
    api(libs.okhttp)
    api(libs.okhttp.logging)
    api(libs.retrofit)
    api(libs.retrofit.converter.moshi)
    implementation(libs.moshi.kotlin)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.timber)
}
