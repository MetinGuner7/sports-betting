package com.sports.component.data.service

import com.sports.component.data.model.SportDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BulletinService {
    @GET("sports/")
    suspend fun getSports(): Response<List<SportDto>>
}