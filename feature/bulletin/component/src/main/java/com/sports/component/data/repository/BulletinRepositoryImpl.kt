package com.sports.component.data.repository

import com.sports.component.data.model.EventOddsDto
import com.sports.component.data.model.EventOddsRequest
import com.sports.component.data.model.toDomainModel
import com.sports.component.data.model.toEventDetailDomainModel
import com.sports.component.data.service.BulletinService
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.model.SportDomainModel
import com.sports.component.domain.repository.BulletinRepository
import com.sports.network.util.bodyOrThrowApiException
import com.squareup.moshi.Moshi
import javax.inject.Inject

class BulletinRepositoryImpl
@Inject
constructor(
    private val bulletinService: BulletinService,
    private val moshi: Moshi
) : BulletinRepository {
    override suspend fun getSports(): Result<List<SportDomainModel>> {
        return runCatching {
            bulletinService.getSports()
                .bodyOrThrowApiException(moshi)
                .toDomainModel()
        }
    }

    override suspend fun getOdds(
        request: EventOddsRequest,
    ): Result<List<EventDetailDomainModel>> {
        return runCatching {
            bulletinService.getOdds(
                key = request.key
            )
                .bodyOrThrowApiException(moshi)
                .toEventDetailDomainModel()
        }
    }
}
