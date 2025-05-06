package com.sports.component.domain.usecase

import com.sports.common.base.UseCase
import com.sports.common.network.AppDispatchers
import com.sports.common.network.Dispatcher
import com.sports.common.resource.Resource
import com.sports.common.resource.asResource
import com.sports.component.domain.model.SportDomainModel
import com.sports.component.domain.repository.BulletinRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetSportsUseCase
@Inject
constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val bulletinRepository: BulletinRepository,
) : UseCase<Unit, Flow<Resource<List<SportDomainModel>>>> {
    override suspend operator fun invoke(
        params: Unit
    ): Flow<Resource<List<SportDomainModel>>> {
        return flow {
            val data = bulletinRepository.getSports()
            emit(data.getOrThrow())
        }
            .flowOn(ioDispatcher)
            .asResource()
    }
}
