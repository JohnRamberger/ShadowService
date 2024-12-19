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
import javax.tools.StandardLocation

@AutoService(Processor::class)
class ServiceProcessor : AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): Set<String> {
        return setOf(Operation::class.java.canonicalName, Service::class.java.canonicalName)
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        println("Processing annotations")

        val tomlContent = buildString {
            for (element in roundEnv.getElementsAnnotatedWith(Operation::class.java)) {
                val config = element.getAnnotation(Operation::class.java)

                println("Found operation: ${config.name}")

                append("${config.name}\n")
            }
        }

        if (tomlContent.isNotEmpty()) {
            println("Writing operations.toml")
            // Create toml file in build folder

            val file = File("build/generated/resources/operations.toml")
            file.parentFile.mkdirs()
            file.writeText(tomlContent)
        }

        return true
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }
}