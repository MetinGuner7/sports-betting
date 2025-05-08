package com.sports.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.sports.common.network.AppDispatchers
import com.sports.common.network.Dispatcher
import com.sports.common.network.di.ApplicationScope
import com.sports.core.datastore.BetBasketProto
import com.sports.core.datastore.UserInfo
import com.sports.datastore.BetBasketSerializer
import com.sports.datastore.UserInfoSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

private const val USER_INFO_FILE_NAME = "user_info.pb"
private const val BET_BASKET_FILE_NAME = "bet_basket.pb"

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideBetBasketDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        betBasketSerializer: BetBasketSerializer
    ): DataStore<BetBasketProto> {
        return DataStoreFactory.create(
            serializer = betBasketSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
            produceFile = { context.dataStoreFile(BET_BASKET_FILE_NAME) }
        )
    }

    @Provides
    @Singleton
    fun providesUserInfoDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        userInfoSerializer: UserInfoSerializer,
    ): DataStore<UserInfo> =
        DataStoreFactory.create(
            serializer = userInfoSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
            produceFile = {
                context.dataStoreFile(USER_INFO_FILE_NAME)
            }
        )
}
