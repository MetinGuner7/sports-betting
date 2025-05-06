package com.sports.network.util

import java.io.IOException

class ApiException(
    val code: Int,
    val httpMessage: String?,
    val errorBody: String? = null,
    val apiErrorMessage: String? = null
) : IOException("HTTP $code ${httpMessage ?: apiErrorMessage ?: errorBody ?: ""}")

data class EmptyBodyException(override val message: String = "Response body was null") : IOException(message)