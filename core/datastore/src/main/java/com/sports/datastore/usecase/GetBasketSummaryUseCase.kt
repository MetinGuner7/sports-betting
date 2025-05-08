package com.sports.datastore.usecase

import com.sports.common.base.UseCase
import com.sports.datastore.model.BasketSummary
import com.sports.datastore.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBasketSummaryUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) : UseCase<Unit, Flow<BasketSummary>> {

    override suspend operator fun invoke(params: Unit): Flow<BasketSummary> {
        return basketRepository.getBasketSummary()
    }
}