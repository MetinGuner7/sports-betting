package com.sports.component.data.service

import com.sports.component.data.model.EventOddsDto
import com.sports.component.data.model.SportDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BulletinService {
    @GET("sports/")
    suspend fun getSports(): Response<List<SportDto>>

    @GET("sports/{sport_key}/odds")
    suspend fun getOdds(
        @Path("sport_key") key: String,
        @Query("regions") regions: String = "eu",
        @Query("markets") markets: String? = null,
        @Query("oddsFormat") oddsFormat: String = "decimal"
    ): Response<List<EventOddsDto>>
}
