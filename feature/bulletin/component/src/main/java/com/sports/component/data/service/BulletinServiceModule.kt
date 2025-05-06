package com.sports.component.data.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BulletinServiceModule {
    @Provides
    @Singleton
    fun provideBulletinService(retrofit: Retrofit): BulletinService {
        return retrofit.create(BulletinService::class.java)
    }
}
