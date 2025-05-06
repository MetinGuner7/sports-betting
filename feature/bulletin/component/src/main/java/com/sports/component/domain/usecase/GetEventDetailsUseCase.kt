package com.sports.component.domain.usecase

import com.sports.common.base.UseCase
import com.sports.common.network.AppDispatchers
import com.sports.common.network.Dispatcher
import com.sports.common.resource.Resource
import com.sports.common.resource.asResource
import com.sports.component.data.model.EventOddsRequest
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.repository.BulletinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetEventDetailsUseCase
@Inject
constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val bulletinRepository: BulletinRepository,
) : UseCase<EventOddsRequest, Flow<Resource<List<EventDetailDomainModel>>>> {
    override suspend operator fun invoke(
        params: EventOddsRequest
    ): Flow<Resource<List<EventDetailDomainModel>>> {
        return flow {
            val data = bulletinRepository.getOdds(request = params)
            emit(data.getOrThrow())
        }
            .flowOn(ioDispatcher)
            .asResource()
    }
}
