package com.sports.datastore.usecase

import com.sports.common.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import com.sports.common.network.AppDispatchers
import com.sports.common.network.Dispatcher
import com.sports.datastore.repository.BasketRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClearBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, Result<Unit>> {

    override suspend operator fun invoke(params: Unit): Result<Unit> {
        return withContext(ioDispatcher) {
            basketRepository.clearBasket()
        }
    }
}