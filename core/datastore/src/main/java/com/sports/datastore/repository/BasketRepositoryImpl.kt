package com.sports.datastore.repository

import com.sports.core.datastore.BasketItemProto
import com.sports.datastore.BasketLocalDataSource
import com.sports.datastore.model.BasketItem
import com.sports.datastore.model.BasketSummary
import com.sports.datastore.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val localDataSource: BasketLocalDataSource
) : BasketRepository {
    override fun getBasketItems(): Flow<List<BasketItem>> {
        return localDataSource.getBasketItems().map { protoList ->
            protoList.map { it.toDomainModel() }
        }
    }

    override fun getBasketSummary(): Flow<BasketSummary> {
        return getBasketItems().map { items ->
            val totalOdds = if (items.isEmpty()) 1.0 else items.fold(1.0) { acc, item -> acc * item.outcomePrice }
            BasketSummary(itemCount = items.size, totalOdds = if (items.isEmpty()) 0.0 else totalOdds)
        }
    }

    override suspend fun addItemToBasket(item: BasketItemProto): Result<Unit> {
        return runCatching { localDataSource.addItem(item) }
    }

    override suspend fun removeItemFromBasket(selectionId: String): Result<Unit> {
        return runCatching { localDataSource.removeItem(selectionId) }
    }

    override suspend fun clearBasket(): Result<Unit> {
        return runCatching { localDataSource.clearBasket() }
    }
}