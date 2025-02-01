package com.jramberger.shadow.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class ShadowPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // target.plugins.apply("kotlin-kapt")

//        target.dependencies.apply {
//            add("kapt", target.project(":processor"))
//            add("implementation", target.project(":annotation"))
//        }

        // Add plugin configuration
        target.extensions.create("shadowConfig", ServiceProcessorExtension::class.java)

//        target.afterEvaluate {
//            val shadowConfig = target.extensions.getByType(ServiceProcessorExtension::class.java)
//            it.tasks.withType(JavaCompile::class.java).apply {
//                add
//            }
//        }
    }
}
