package com.sports.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OddsApiErrorDto(
    @Json(name = "message") val message: String?
)