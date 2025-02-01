package com.jramberger.shadow.service.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Tracing {
    @SerialName("Active")
    ACTIVE,

    @SerialName("PassThrough")
    PASS_THROUGH,

    @SerialName("Disabled")
    DISABLED,
}
