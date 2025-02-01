package com.jramberger.shadow.service.model.function

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Method {
    @SerialName("get")
    GET,

    @SerialName("post")
    POST,

    @SerialName("put")
    PUT,

    @SerialName("delete")
    DELETE,

    @SerialName("patch")
    PATCH,

    @SerialName("head")
    HEAD,

    @SerialName("options")
    OPTIONS,

    @SerialName("any")
    ANY,
}
