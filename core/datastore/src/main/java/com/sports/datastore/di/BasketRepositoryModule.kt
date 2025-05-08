package com.sports.datastore.di

import com.sports.datastore.BasketLocalDataSource
import com.sports.datastore.BasketLocalDataSourceImpl
import com.sports.datastore.repository.BasketRepository
import com.sports.datastore.repository.BasketRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BasketRepositoryModule {
    @Binds
    @Singleton
    fun bindBasketRepository(
        basketRepositoryImpl: BasketRepositoryImpl
    ): BasketRepository

    @Binds
    @Singleton
    fun bindBasketLocalDataSource(
        basketLocalDataSourceImpl: BasketLocalDataSourceImpl
    ): BasketLocalDataSource

}
