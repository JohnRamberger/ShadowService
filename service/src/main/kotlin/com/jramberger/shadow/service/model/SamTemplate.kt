package com.jramberger.shadow.service.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SamTemplate(
    @SerialName("Globals")
    val globals: Globals = Globals(),
    @SerialName("Description")
    val description: String = "SAM Template for Shadow Service",
    @SerialName("Resources")
    val resources: Map<String, Resource> = emptyMap(),
) {
    @SerialName("Transform")
    val transform = "AWS::Serverless-2016-10-31"
}
