package com.sports.datastore.usecase

import com.sports.common.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import com.sports.common.network.AppDispatchers
import com.sports.common.network.Dispatcher
import com.sports.common.resource.Resource
import com.sports.common.resource.asResource
import com.sports.datastore.model.mapToBasketItemProto
import com.sports.datastore.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddBetToBasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UseCase<AddBetToBasketUseCase.Params, Flow<Resource<Unit>>> {
    data class Params(
        val eventId: String,
        val marketKey: String,
        val name: String,
        val price: Double,
        val homeTeam: String,
        val awayTeam: String,
        val selectionId: String,
        val bookmakerKey: String,
    )
    override suspend fun invoke(params: Params): Flow<Resource<Unit>> {
        return flow {
            val basketItemProto = mapToBasketItemProto(
                eventId = params.eventId,
                marketKey = params.marketKey,
                price = params.price,
                name = params.name,
                homeTeam = params.homeTeam,
                awayTeam = params.awayTeam,
                selectionId = params.selectionId,
                bookmakerKey = params.bookmakerKey
            )
            basketRepository.addItemToBasket(basketItemProto)
            emit(Unit)
        }
            .flowOn(ioDispatcher)
            .asResource()
    }
}
