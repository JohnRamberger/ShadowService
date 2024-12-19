package com.jramberger.shadow.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class ShadowPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("kotlin-kapt")

        target.tasks.register("something") {
            it.doLast {
                println("Hello from ShadowPlugin")
            }
        }

        target.dependencies.apply {
            add("kapt", target.project(":processor"))
            add("implementation", target.project(":annotation"))
        }

        // Add plugin configuration
        target.extensions.create("serviceProcessor", ServiceProcessorExtension::class.java)
    }
}
