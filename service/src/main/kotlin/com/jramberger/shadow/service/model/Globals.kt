package com.jramberger.shadow.service.model

import com.jramberger.shadow.service.model.function.FunctionConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Globals(
    @SerialName("Function")
    val function: FunctionConfig? = null,
)
