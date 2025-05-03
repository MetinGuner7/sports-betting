package com.sports.betting.build_logic.convention

import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsPlugin
import com.sports.betting.build_logic.convention.template.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class FirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) { apply(CrashlyticsPlugin::class.java) }

            dependencies {
                add("implementation", platform(libs.findLibrary("firebase.bom").get()))
                add("implementation", libs.findLibrary("firebase.analytics").get())
                add("implementation", libs.findLibrary("firebase.crashlytics").get())
            }
        }
    }
}
