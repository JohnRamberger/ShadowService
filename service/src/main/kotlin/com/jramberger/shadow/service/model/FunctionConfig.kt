package com.jramberger.shadow.service.model

import java.time.Duration

// https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/sam-resource-function.html
data class FunctionConfig(
    val runtime: JavaRuntime,
    val tracing: Boolean? = false,
    val additionalArgs: AdditionalArgs = emptyMap(),
    val environment: Map<String, String> = emptyMap(),
    val memorySize: Int = 128,
    val timeout: Duration = Duration.ofSeconds(30),
    val layers: List<String> = emptyList(),
    val description: String? = null,
    val tags: Map<String, String> = emptyMap(),
    val reservedConcurrentExecutions: Int? = null,
    val role: String? = null,
    val policies: List<String> = emptyList(),
)
