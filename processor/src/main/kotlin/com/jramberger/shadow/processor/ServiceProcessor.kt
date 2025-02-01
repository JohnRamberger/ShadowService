package com.jramberger.shadow.processor

import com.charleskorn.kaml.PolymorphismStyle
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import com.google.auto.service.AutoService
import com.jramberger.shadow.annotation.Operation
import com.jramberger.shadow.annotation.Service
import com.jramberger.shadow.service.ShadowService
import com.jramberger.shadow.service.model.Globals
import com.jramberger.shadow.service.model.JavaRuntime
import com.jramberger.shadow.service.model.Resource
import com.jramberger.shadow.service.model.SamTemplate
import com.jramberger.shadow.service.model.function.Event
import com.jramberger.shadow.service.model.function.FunctionConfig
import com.jramberger.shadow.service.model.function.FunctionProperties
import com.jramberger.shadow.service.model.function.HttpApiProperties
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
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
        val shadowServiceType =
            processingEnv.elementUtils.getTypeElement(ShadowService::class.java.canonicalName).asType()

        val service: Pair<Element, Service>? =
            roundEnv.getElementsAnnotatedWith(Service::class.java).map { it to it.getAnnotation(Service::class.java) }
                .firstOrNull {
                    processingEnv.typeUtils.isAssignable(it.first.asType(), shadowServiceType)
                }

        val operations =
            roundEnv.getElementsAnnotatedWith(Operation::class.java).map {
                it to it.getAnnotation(Operation::class.java)
            }

//        val tomlContent = buildString {
//            for (element in ) {
//                val config = element.getAnnotation(Operation::class.java)
//
//                // get method path (for ex com.jramberger.testshadowservice.activity.HelloWorldActivity::handle)
//                val methodPath = element.enclosingElement.toString() + "::" + element.simpleName
//                println("Found operation: ${config.name}")
//
//                append("${config.name} = ${methodPath}\n")
//            }
//        }

//        if (tomlContent.isNotEmpty()) {
//            val fileName = "operations.toml"
//
//            println("Writing operations.toml")
//            // Create toml file in build folder
//
//            val file = File(shadowOutputDir + fileName)
//            file.parentFile.mkdirs()
//            file.writeText(tomlContent)
//        }

        if (service != null) {
            println("Found service: ${service.second.name}")
            buildTemplate(service, operations)
            buildConfig()

            return true
        } else {
            println("No service found")
            return false
        }
    }

    private fun buildTemplate(
        service: Pair<Element, Service>,
        operations: List<Pair<Element, Operation>>,
    ) {
        val outputDir = ProcessorProperties.outputDir
        val outputFile = ProcessorProperties.templateFile

//        val shadowService = processingEnv.typeUtils.asElement(service.first.asType()) as ShadowService

        val template =
            SamTemplate(
                globals = Globals(
                    function = FunctionConfig(
                        runtime = JavaRuntime.JAVA_17,
                        timeoutInSeconds = 30,
                        memorySize = 128,
                    )
                ),
                resources =
                operations.associate { (el, op) ->
                    val funcName = op.name + "Function"
                    funcName to
                            Resource.Function(
                                properties =
                                FunctionProperties(
                                    codeUri = "./",
                                    handler = "${el.enclosingElement}::${el.simpleName}",
                                    events =
                                    mapOf(
                                        op.name to
                                                Event.HttpApi(
                                                    properties =
                                                    HttpApiProperties(
                                                        path = op.path,
                                                        method = op.method,
                                                    ),
                                                ),
                                    ),
                                ),
                            )
                }.toMap(),
            )

        println("Writing $outputFile")
        val yamlString = Yaml(
            configuration = YamlConfiguration(
                encodeDefaults = false,
                polymorphismStyle = PolymorphismStyle.None,
            )
        ).encodeToString(SamTemplate.serializer(), template)

        val file = File(outputDir + outputFile)
        file.parentFile.mkdirs()
        file.writeText(yamlString)
    }

    private fun buildConfig() {
        val file = File(ProcessorProperties.configFile)

        val content = """
            # More information about the configuration file can be found here:
            # https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-config.html
            version = 0.1
            
            [default.global.parameters]
            stack_name = "sam-app"
            
            [default.build.parameters]
            cached = true
            parallel = true
            
            [default.validate.parameters]
            lint = true
            
            [default.deploy.parameters]
            capabilities = "CAPABILITY_IAM"
            confirm_changeset = true
            resolve_s3 = true
            
            [default.package.parameters]
            resolve_s3 = true
            
            [default.sync.parameters]
            watch = true
            
            [default.local_start_api.parameters]
            warm_containers = "EAGER"
            
            [default.local_start_lambda.parameters]
            warm_containers = "EAGER"
        """.trimIndent()

        println("Writing ${ProcessorProperties.configFile}")
        file.writeText(content)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }
}
