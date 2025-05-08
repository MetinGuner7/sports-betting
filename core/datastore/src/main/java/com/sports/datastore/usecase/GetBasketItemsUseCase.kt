package com.sports.datastore.usecase

import com.sports.common.base.UseCase
import com.sports.datastore.model.BasketItem
import com.sports.datastore.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBasketItemsUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) : UseCase<Unit, Flow<List<BasketItem>>> {

    override suspend operator fun invoke(params: Unit): Flow<List<BasketItem>> {
        return basketRepository.getBasketItems()
    }
}
