package com.jramberger.shadow.service.model

import com.jramberger.shadow.service.model.function.FunctionConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Resource {
    @SerialName("Type")
    open val type: String = ""

    // https://github.com/aws/serverless-application-model/blob/master/versions/2016-10-31.md#awsserverlessfunction
    @Serializable
    data class Function(
        @SerialName("Properties")
        val properties: FunctionConfig,
    ) : Resource() {
        override val type: String = "AWS::Serverless::Function"
    }
}
