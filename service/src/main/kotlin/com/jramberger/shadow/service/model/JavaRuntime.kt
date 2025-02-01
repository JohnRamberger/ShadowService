package com.jramberger.shadow.service.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class JavaRuntime() {
    @SerialName("java8.al2")
    JAVA_8,

    @SerialName("java11")
    JAVA_11,

    @SerialName("java17")
    JAVA_17,

    @SerialName("java21")
    JAVA_21,
}
