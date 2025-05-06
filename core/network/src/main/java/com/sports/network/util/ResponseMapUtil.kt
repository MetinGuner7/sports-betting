package com.sports.network.util

import com.sports.network.model.OddsApiErrorDto
import com.squareup.moshi.Moshi
import retrofit2.Response
import timber.log.Timber


fun <T : Any> Response<T>.bodyOrThrowApiException(moshi: Moshi): T {
    if (this.isSuccessful) {
        return this.body() ?: throw EmptyBodyException(
            "Successful response but body was null for"
        )
    } else {
        val errorCode = this.code()
        val httpErrorMessage = this.message()
        val errorBodyString = this.errorBody()?.string()
        this.errorBody()?.close()

        var parsedApiErrorMessage: String? = null
        if (!errorBodyString.isNullOrBlank()) {
            try {
                val errorAdapter = moshi.adapter(OddsApiErrorDto::class.java)
                parsedApiErrorMessage = errorAdapter.fromJson(errorBodyString)?.message
            } catch (e: Exception) {
                Timber.w(e, "API error body parse edilemedi: $errorBodyString")
            }
        }

        throw ApiException(
            code = errorCode,
            httpMessage = httpErrorMessage,
            errorBody = errorBodyString,
            apiErrorMessage = parsedApiErrorMessage
        )
    }
}
