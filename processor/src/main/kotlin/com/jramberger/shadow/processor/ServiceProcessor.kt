package com.jramberger.shadow.processor

import com.google.auto.service.AutoService
import com.jramberger.shadow.annotation.Operation
import com.jramberger.shadow.annotation.Service
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class ServiceProcessor : AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): Set<String> {
        return setOf(Operation::class.java.canonicalName, Service::class.java.canonicalName)
    }

    override fun process(
        annotations: MutableSet<out TypeElement>,
        roundEnv: RoundEnvironment,
    ): Boolean {
        var service: Service? = null

        for (element in roundEnv.getElementsAnnotatedWith(Service::class.java)) {
            service = element.getAnnotation(Service::class.java)
        }

        var operations = mutableListOf<Operation>()

        val tomlContent =
            buildString {
                for (element in roundEnv.getElementsAnnotatedWith(Operation::class.java)) {
                    val config = element.getAnnotation(Operation::class.java)

                    // get method path (for ex com.jramberger.testshadowservice.activity.HelloWorldActivity::handle)
                    val methodPath = element.enclosingElement.toString() + "::" + element.simpleName
                    println("Found operation: ${config.name}")

                    append("${config.name} = ${methodPath}\n")
                }
            }

        val shadowOutputDir = System.getProperty("shadow.output.dir") ?: "build/shadow/"

        if (tomlContent.isNotEmpty()) {
            val fileName = "operations.toml"

            println("Writing operations.toml")
            // Create toml file in build folder

            val file = File(shadowOutputDir + fileName)
            file.parentFile.mkdirs()
            file.writeText(tomlContent)
        }

        return true
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }
}
