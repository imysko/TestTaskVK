package com.imysko.testtaskvk.data.entities

class RequestException(
    val statusCode: Int,
    message: String,
) : Throwable(message)
