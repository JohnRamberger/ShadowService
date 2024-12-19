package com.jramberger.shadow.processor

import com.google.auto.service.AutoService
import com.jramberger.shadow.annotation.Operation
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import javax.tools.StandardLocation

@AutoService(Processor::class)
class ServiceProcessor : AbstractProcessor() {
    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val tomlContent = buildString {
            for (element in roundEnv.getElementsAnnotatedWith(Operation::class.java)) {
                val config = element.getAnnotation(Operation::class.java)
                append("${config.name}\n")
            }
        }

        if (tomlContent.isNotEmpty()) {
            val file = processingEnv.filer.createResource(
                StandardLocation.CLASS_OUTPUT,
                "",
                "operations.toml"
            )
            file.openWriter().use { it.write(tomlContent) }
        }

        return true
    }
}