package com.sports.datastore.repository

import com.sports.core.datastore.BasketItemProto
import com.sports.datastore.model.BasketItem
import com.sports.datastore.model.BasketSummary
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    fun getBasketItems(): Flow<List<BasketItem>>
    fun getBasketSummary(): Flow<BasketSummary>
    suspend fun addItemToBasket(item: BasketItemProto): Result<Unit>
    suspend fun removeItemFromBasket(selectionId: String): Result<Unit>
    suspend fun clearBasket(): Result<Unit>
}