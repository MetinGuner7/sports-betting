package com.sports.datastore.usecase

import com.sports.common.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import com.sports.common.network.AppDispatchers
import com.sports.common.network.Dispatcher
import com.sports.common.resource.Resource
import com.sports.common.resource.asResource
import com.sports.datastore.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoveBetFromBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UseCase<String, Flow<Resource<Unit>>> {

    override suspend fun invoke(params: String): Flow<Resource<Unit>> {
        return flow {
            basketRepository.removeItemFromBasket(params)
            emit(Unit)
        }
            .flowOn(ioDispatcher)
            .asResource()
    }
}