package com.sports.auth.component.domain.repository

import com.sports.auth.component.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observeAuthState(): Flow<Boolean>
    suspend fun getCurrentUser(): Result<Boolean>
    suspend fun signInWithEmailPassword(email: String, password: String): Result<AuthUser>
    suspend fun registerWithEmailPassword(email: String, password: String): Result<AuthUser>
    suspend fun signOut(): Result<Unit>
}
