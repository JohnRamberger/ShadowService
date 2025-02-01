package com.jramberger.shadow.annotation

import com.jramberger.shadow.service.model.function.Method

annotation class Operation(
    val name: String,
    val path: String,
    val method: Method,
)
