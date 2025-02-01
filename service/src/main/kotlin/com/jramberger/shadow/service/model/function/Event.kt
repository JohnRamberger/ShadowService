package com.jramberger.shadow.service.model.function

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// https://github.com/aws/serverless-application-model/blob/master/versions/2016-10-31.md#event-source-object
@Serializable
sealed class Event {
    @SerialName("Type")
    open val type: String = ""

    // https://github.com/aws/serverless-application-model/blob/master/versions/2016-10-31.md#httpapi
    @Serializable
    data class HttpApi(
        @SerialName("Properties")
        val properties: HttpApiProperties,
    ) : Event() {
        override val type: String = "HttpApi"
    }
}
