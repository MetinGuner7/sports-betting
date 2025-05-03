package com.sports.betting.build_logic.convention

import com.android.build.gradle.TestExtension
import com.sports.betting.build_logic.convention.template.configureKotlinAndroid
import com.sports.betting.build_logic.convention.template.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("compileSdk").get().toString().toInt()
            }
        }
    }
}
