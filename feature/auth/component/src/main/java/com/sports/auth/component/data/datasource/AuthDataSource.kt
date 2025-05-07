package com.sports.auth.component.data.datasource

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun observeAuthState(): Flow<Boolean>
    fun getCurrentUser(): FirebaseUser?
    suspend fun signInWithEmailPassword(email: String, password: String): AuthResult
    suspend fun registerWithEmailPassword(email: String, password: String): AuthResult
    // suspend fun signInAnonymously(): AuthResult
    fun signOut()
}

