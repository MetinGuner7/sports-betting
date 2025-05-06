package com.sports.component.data.repository

import com.sports.component.domain.repository.BulletinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BulletinRepositoryModule {
    @Binds @Singleton fun bindBulletinRepository(
        bulletinRepositoryImpl: BulletinRepositoryImpl)
    : BulletinRepository
}
