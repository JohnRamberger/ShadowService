package com.jramberger.shadow.service.model.function

import kotlinx.serialization.Serializable

@Serializable
data class HttpApiProperties(
    val path: String,
    val method: Method,
)
