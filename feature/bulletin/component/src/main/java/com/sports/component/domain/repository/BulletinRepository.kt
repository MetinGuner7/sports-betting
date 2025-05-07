package com.sports.component.domain.repository

import com.sports.component.data.model.EventOddsDto
import com.sports.component.data.model.EventOddsRequest
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.model.SportDomainModel

interface BulletinRepository {
    suspend fun getSports(): Result<List<SportDomainModel>>

    suspend fun getOdds(request: EventOddsRequest): Result<List<EventDetailDomainModel>>

}
