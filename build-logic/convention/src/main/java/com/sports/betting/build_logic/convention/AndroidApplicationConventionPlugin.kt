package com.sports.betting.build_logic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.project.starter.easylauncher.plugin.EasyLauncherExtension
import com.project.starter.easylauncher.plugin.EasyLauncherPlugin
import com.sports.betting.build_logic.convention.template.Flavor
import com.sports.betting.build_logic.convention.template.configureFlavors
import com.sports.betting.build_logic.convention.template.configureKotlinAndroid
import com.sports.betting.build_logic.convention.template.configurePrintApksTask
import com.sports.betting.build_logic.convention.template.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType


class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply(EasyLauncherPlugin::class)
                apply("sports.firebase")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("compileSdk").get().toString().toInt()
                configureFlavors(this)
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }

            extensions.configure<EasyLauncherExtension> {
                with(productFlavors) {
                    register(Flavor.dev.name) {
                        filters(
                            chromeLike(
                                label = Flavor.dev.name.uppercase(),
                                overlayHeight = 0.3F,
                                textSizeRatio = 0.2F,
                            )
                        )
                    }
                    register(Flavor.alpha.name) {
                        filters(
                            chromeLike(
                                label = Flavor.alpha.name.uppercase(),
                                overlayHeight = 0.3F,
                                textSizeRatio = 0.2F,
                            )
                        )
                    }
                    register(Flavor.beta.name) {
                        filters(
                            chromeLike(
                                label = Flavor.beta.name.uppercase(),
                                overlayHeight = 0.3F,
                                textSizeRatio = 0.2F,
                            )
                        )
                    }
                }
            }
        }
    }
}
