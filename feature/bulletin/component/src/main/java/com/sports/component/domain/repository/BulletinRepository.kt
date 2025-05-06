package com.sports.component.domain.repository

import com.sports.component.domain.model.SportDomainModel

interface BulletinRepository {
    suspend fun getSports(): Result<List<SportDomainModel>>

}
