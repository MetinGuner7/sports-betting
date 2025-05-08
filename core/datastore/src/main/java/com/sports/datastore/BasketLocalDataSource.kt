package com.sports.datastore


import androidx.datastore.core.DataStore
import com.sports.core.datastore.BasketItemProto
import com.sports.core.datastore.BetBasketProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface BasketLocalDataSource {
    fun getBasketItems(): Flow<List<BasketItemProto>>
    suspend fun addItem(item: BasketItemProto)
    suspend fun removeItem(selectionId: String)
    suspend fun clearBasket()
}

class BasketLocalDataSourceImpl @Inject constructor(
    private val betBasketDataStore: DataStore<BetBasketProto>
) : BasketLocalDataSource {
    override fun getBasketItems(): Flow<List<BasketItemProto>> {
        return betBasketDataStore.data.map { it.itemsList }
    }

    override suspend fun addItem(item: BasketItemProto) {
        betBasketDataStore.updateData { currentBasket ->
            // Aynı selectionId ile zaten varsa güncelleme veya üzerine yazma (bu örnekte ekliyor)
            // Daha gelişmiş mantık: Eğer aynı maçın aynı marketinden farklı bir outcome seçilirse
            // eskisini silip yenisini ekle veya kullanıcıya uyar. Şimdilik basit ekleme.
            val existingItem = currentBasket.itemsList.find { it.selectionId == item.selectionId }
            if (existingItem == null) { // Sadece yoksa ekle
                currentBasket.toBuilder().addItems(item).build()
            } else {
                // İstersen var olanı güncelle veya hiçbir şey yapma
                currentBasket // Veya hata fırlat/mesaj göster
            }
        }
    }

    override suspend fun removeItem(selectionId: String) {
        betBasketDataStore.updateData { currentBasket ->
            val updatedItems = currentBasket.itemsList.filterNot { it.selectionId == selectionId }
            currentBasket.toBuilder().clearItems().addAllItems(updatedItems).build()
        }
    }

    override suspend fun clearBasket() {
        betBasketDataStore.updateData { it.toBuilder().clearItems().build() }
    }
}