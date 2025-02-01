package com.jramberger.shadow.service.model.function

import com.jramberger.shadow.service.model.JavaRuntime
import com.jramberger.shadow.service.model.Tracing
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/sam-resource-function.html
@Serializable
data class FunctionConfig(
    @SerialName("CodeUri")
    val codeUri: String? = null,
    @SerialName("Handler")
    val handler: String? = null,
    @SerialName("Runtime")
    val runtime: JavaRuntime? = null,
    @SerialName("Tracing")
    val tracing: Tracing = Tracing.DISABLED,
//    val additionalArgs: AdditionalArgs = emptyMap(),
//    val environment: Map<String, String> = emptyMap(),
    @SerialName("MemorySize")
    val memorySize: Int? = null,
    @SerialName("Timeout")
    val timeoutInSeconds: Int? = null,
//    @SerialName("Layers")
//    val layers: List<String> = emptyList(),
    @SerialName("Description")
    val description: String? = null,
    @SerialName("Tags")
    val tags: Map<String, String> = emptyMap(),
    @SerialName("ReservedConcurrentExecutions")
    val reservedConcurrentExecutions: Int? = null,
//    val role: String? = null,
//    val policies: List<String> = emptyList(),
    @SerialName("Events")
    val events: Map<String, Event> = emptyMap(),
)
